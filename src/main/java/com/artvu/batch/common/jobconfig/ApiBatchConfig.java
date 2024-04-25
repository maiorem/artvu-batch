//package com.artvu.batch.common.jobconfig;
//
//import com.artvu.batch.artdetail.application.ArtDetailService;
//import com.artvu.batch.artdetail.presentation.KopisArtDetailResponse;
//import com.artvu.batch.artdetail.application.ArtDetailItemProcessor;
//import com.artvu.batch.artdetail.application.ArtDetailItemReader;
//import com.artvu.batch.artdetail.application.ArtDetailItemWriter;
//import com.artvu.batch.artlist.application.ArtListItemProcessor;
//import com.artvu.batch.artlist.application.ArtListItemReader;
//import com.artvu.batch.artlist.application.ArtListItemWriter;
//import com.artvu.batch.artlist.application.ArtListService;
//import com.artvu.batch.artlist.presentation.KopisArtListResponse;
//import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
//import com.artvu.batch.artlist.domain.entity.KopisArtList;
//import com.artvu.batch.facdetail.application.ArtFacItemProcessor;
//import com.artvu.batch.facdetail.application.ArtFacItemReader;
//import com.artvu.batch.facdetail.application.ArtFacItemWriter;
//import com.artvu.batch.facdetail.domain.entity.KopisFacDetail;
//import com.artvu.batch.facdetail.presentation.KopisFacDetailResponse;
//import jakarta.persistence.EntityManagerFactory;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.database.JpaItemWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//import java.util.List;
//
//@Slf4j
//@Configuration
//@RequiredArgsConstructor
//public class ApiBatchConfig {
//    private final EntityManagerFactory entityManager;
//
//    private final ArtListService artListService;
//
//    private final ArtDetailService artDetailService;
//
//    private final JobRepository jobRepository;
//
//    private final PlatformTransactionManager transactionManager;
//
//    @Bean
//    public Job listReaderJob(Step listStep, Step detailStep){
//
//        return new JobBuilder("listReaderJob", jobRepository)
//                .start(listStep)
//                .next(detailStep)
//                .incrementer(new RunIdIncrementer())
//                .build();
//    }
//
//    // Step 1 (list)
//    @Bean
//    @JobScope
//    public Step listStep() {
//        return new StepBuilder("listStep", jobRepository)
//                .<KopisArtListResponse, List<KopisArtList>>chunk(100, transactionManager)
//                .reader(listItemReader())
//                .processor(listItemProcessor())
//                .writer(listItemWriter())
//                .allowStartIfComplete(true)
//                .build();
//    }
//
//    // Step 2 (detail)
//    @Bean
//    @JobScope
//    public Step detailStep() {
//        return new StepBuilder("detailStep", jobRepository)
//                .<List<KopisArtDetailResponse>, List<KopisArtDetail>>chunk(100, transactionManager)
//                .reader(detailItemReader())
//                .processor(detailItemProcessor())
//                .writer(detailItemWriter())
//                .allowStartIfComplete(true)
//                .build();
//    }
//
//    // Step 3 (facility)
//    @Bean
//    @JobScope
//    public Step facStep() {
//        return new StepBuilder("facStep", jobRepository)
//                .<List<KopisFacDetailResponse>, List<KopisFacDetail>>chunk(100, transactionManager)
//                .reader(facItemReader())
//                .processor(facItemProcessor())
//                .writer(facItemWriter())
//                .allowStartIfComplete(true)
//                .build();
//    }
//
//    // ============ Step 1. List =================
//    @Bean
//    @StepScope
//    public ArtListItemReader listItemReader() {
//        return new ArtListItemReader();
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor<KopisArtListResponse, List<KopisArtList>> listItemProcessor() {
//        return new ArtListItemProcessor();
//    }
//
//    @Bean
//    @StepScope
//    public ArtListItemWriter<KopisArtList> listItemWriter() {
//        JpaItemWriter<KopisArtList> writer = new JpaItemWriter<>();
//        writer.setEntityManagerFactory(entityManager);
//        return new ArtListItemWriter<>(writer);
//    }
//
//    // ============ Step 2. Detail =================
//    @Bean
//    @StepScope
//    public ArtDetailItemReader detailItemReader() {
//        return new ArtDetailItemReader(artListService);
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor<List<KopisArtDetailResponse>, List<KopisArtDetail>> detailItemProcessor() {
//        return new ArtDetailItemProcessor();
//    }
//
//    @Bean
//    @StepScope
//    public ArtDetailItemWriter<KopisArtDetail> detailItemWriter() {
//        JpaItemWriter<KopisArtDetail> writer = new JpaItemWriter<>();
//        writer.setEntityManagerFactory(entityManager);
//        return new ArtDetailItemWriter<>(writer);
//    }
//
//    // ============ Step 3. facility =================
//    @Bean
//    @StepScope
//    public ArtFacItemReader facItemReader() {
//        return new ArtFacItemReader(artDetailService);
//    }
//
//    @Bean
//    @StepScope
//    public ItemProcessor<List<KopisFacDetailResponse>, List<KopisFacDetail>> facItemProcessor() {
//        return new ArtFacItemProcessor();
//    }
//
//    @Bean
//    @StepScope
//    public ArtFacItemWriter<KopisArtDetail> facItemWriter() {
//        JpaItemWriter<KopisFacDetail> writer = new JpaItemWriter<>();
//        writer.setEntityManagerFactory(entityManager);
//        return new ArtFacItemWriter<>(writer);
//    }
//
//
//    // ============ Step 4. best price ===========
//
//
//    // ============ Step 5. update artvu db  ===========
//
//
//}
