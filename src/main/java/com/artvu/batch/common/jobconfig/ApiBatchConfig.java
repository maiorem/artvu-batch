package com.artvu.batch.common.jobconfig;

import com.artvu.batch.artdetail.application.ArtDetailService;
import com.artvu.batch.artdetail.presentation.KopisArtDetailResponse;
import com.artvu.batch.artdetail.application.ArtDetailItemProcessor;
import com.artvu.batch.artdetail.application.ArtDetailItemReader;
import com.artvu.batch.artdetail.application.ArtDetailItemWriter;
import com.artvu.batch.artlist.application.*;
import com.artvu.batch.artlist.presentation.KopisArtListResponse;
import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import com.artvu.batch.artlist.domain.entity.KopisArtList;
import com.artvu.batch.artvu.application.ArtvuArtService;
import com.artvu.batch.artvu.application.DbTransferProcessor;
import com.artvu.batch.artvu.application.DbTransferWriter;
import com.artvu.batch.artvu.application.UpdateDataTasklet;
import com.artvu.batch.artvu.domain.entity.ArtList;
import com.artvu.batch.facdetail.application.ArtFacItemProcessor;
import com.artvu.batch.facdetail.application.ArtFacItemReader;
import com.artvu.batch.facdetail.application.ArtFacItemWriter;
import com.artvu.batch.facdetail.domain.entity.KopisFacDetail;
import com.artvu.batch.facdetail.presentation.KopisFacDetailResponse;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ApiBatchConfig {

    private final EntityManagerFactory entityManager;

    private final ArtListService artListService;

    private final ArtDetailService artDetailService;

    private final ArtvuArtService artvuArtService;

    private final JobRepository jobRepository;

    private final PlatformTransactionManager transactionManager;

    @Bean(name = "artReaderJob")
    public Job artReaderJob(){

        return new JobBuilder("artReaderJob", jobRepository)
                .start(listStep())
                .next(musicalListStep())
                .next(detailStep())
                .next(theaterDataStep())
                .next(dbTranferStep())
                .next(updateData())
                .incrementer(new RunIdIncrementer())
                .build();
    }

    // Step 1 (list)
    @Bean
    public Step listStep() {
        return new StepBuilder("listStep", jobRepository)
                .<KopisArtListResponse, List<KopisArtList>>chunk(200, transactionManager)
                .reader(listItemReader())
                .processor(listItemProcessor())
                .writer(listItemWriter())
                .allowStartIfComplete(true)
                .build();
    }

    @Bean
    public Step musicalListStep() {
        return new StepBuilder("musicalListStep", jobRepository)
                .<KopisArtListResponse, List<KopisArtList>>chunk(200, transactionManager)
                .reader(musicalListItemReader())
                .processor(musicallistItemProcessor())
                .writer(musicallistItemWriter())
                .allowStartIfComplete(true)
                .build();
    }



    // Step 2 (detail)
    @Bean
    public Step detailStep() {
        return new StepBuilder("detailStep", jobRepository)
                .<List<KopisArtDetailResponse>, List<KopisArtDetail>>chunk(200, transactionManager)
                .reader(detailItemReader())
                .processor(detailItemProcessor())
                .writer(detailItemWriter())
                .allowStartIfComplete(true)
                .build();
    }

    // Step 3 (facility)
    @Bean
    public Step theaterDataStep() {
        return new StepBuilder("theaterDataStep", jobRepository)
                .<List<KopisFacDetailResponse>, List<KopisFacDetail>>chunk(200, transactionManager)
                .reader(facItemReader())
                .processor(facItemProcessor())
                .writer(facItemWriter())
                .allowStartIfComplete(true)
                .build();
    }

    // Step 4. best price crawling




    // Step 5 (DB transfer)
    @Bean
    public Step dbTranferStep(){
        return new StepBuilder("dbTranferStep", jobRepository)
                .<KopisArtList, ArtList>chunk(200, transactionManager)
                .reader(artListDbTransferReader())
                .processor(artListDbTransferProcessor())
                .writer(artListDbTranferWriter())
                .allowStartIfComplete(true)
                .build();
    }

    // Step 6. (Update data for end performs)
    @Bean
    public Step updateData() {
        return new StepBuilder("updateData", jobRepository)
                .tasklet(new UpdateDataTasklet(artvuArtService), transactionManager)
                .allowStartIfComplete(true)
                .build();
    }


    // ============ Step 1. List =================
    @Bean
    public ArtListItemReader listItemReader() {
        return new ArtListItemReader();
    }

    @Bean
    public ArtListItemProcessor listItemProcessor() {
        return new ArtListItemProcessor();
    }

    @Bean
    public ArtListItemWriter<KopisArtList> listItemWriter() {
        JpaItemWriter<KopisArtList> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager);
        return new ArtListItemWriter<>(writer);
    }

    // ==== musical ====

    @Bean
    public ArtMusicalListItemReader musicalListItemReader() {
        return new ArtMusicalListItemReader();
    }

    @Bean
    public ArtMusicalListItemProcessor musicallistItemProcessor() {
        return new ArtMusicalListItemProcessor();
    }

    @Bean
    public ArtMusicalListItemWriter<KopisArtList> musicallistItemWriter() {
        JpaItemWriter<KopisArtList> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager);
        return new ArtMusicalListItemWriter<>(writer);
    }


    // ============ Step 2. Detail =================
    @Bean
    public ArtDetailItemReader detailItemReader() {
        return new ArtDetailItemReader(artListService);
    }

    @Bean
    public ArtDetailItemProcessor detailItemProcessor() {
        return new ArtDetailItemProcessor();
    }

    @Bean
    public ArtDetailItemWriter<KopisArtDetail> detailItemWriter() {
        JpaItemWriter<KopisArtDetail> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager);
        return new ArtDetailItemWriter<>(writer);
    }

    // ============ Step 3. facility =================
    @Bean
    public ArtFacItemReader facItemReader() {
        return new ArtFacItemReader(artDetailService);
    }

    @Bean
    public ArtFacItemProcessor facItemProcessor() {
        return new ArtFacItemProcessor();
    }

    @Bean
    public ArtFacItemWriter<KopisFacDetail> facItemWriter() {
        JpaItemWriter<KopisFacDetail> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager);
        return new ArtFacItemWriter<>(writer);
    }


    // ============ Step 4. best price ===========


    // ============ Step 5. update artvu db  ===========
    @Bean
    public JpaCursorItemReader<KopisArtList> artListDbTransferReader(){
        return new JpaCursorItemReaderBuilder<KopisArtList>()
                .name("artListDbTransferReader")
                .entityManagerFactory(entityManager)
                .queryString("SELECT a FROM KopisArtList a")
                .build();

    }

    @Bean
    public DbTransferProcessor artListDbTransferProcessor() {
        return new DbTransferProcessor();
    }

    @Bean
    public DbTransferWriter<ArtList> artListDbTranferWriter(){
        JpaItemWriter<ArtList> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManager);
        return new DbTransferWriter<>(writer);
    }


}
