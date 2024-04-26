package com.artvu.batch.artdetail.application;

import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import com.artvu.batch.artlist.application.ArtListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ArtDetailItemWriter<T> extends JpaItemWriter<List<KopisArtDetail>> {

    private final JpaItemWriter<KopisArtDetail> jpaItemWriter;

    @Autowired
    private ArtDetailService detailService;

    public ArtDetailItemWriter(JpaItemWriter<KopisArtDetail> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(Chunk<? extends List<KopisArtDetail>> items) {
        log.info("art DETAIL WRITER START ================================================");

        List<String> artIdList = detailService.artIdList();

        Chunk<KopisArtDetail> collectList = new Chunk<>();

        for(List<KopisArtDetail> list : items){
            log.info("art DETAIL writing....1 ================================================");
            List<KopisArtDetail> newList = new ArrayList<>();
            for (KopisArtDetail kopisArtDetail : list) {
                log.info("art DETAIL writing....2 ================================================");
                if ( !artIdList.contains(kopisArtDetail.getArtId())) {
                    newList.add(kopisArtDetail);
                    log.info("art DETAIL writing....complete ================================================");
                }
            }
            collectList.addAll(newList);
        }

        jpaItemWriter.write(collectList);
        log.info("art DETAIL WRITER END ================================================");

    }

    @Override
    public void afterPropertiesSet()  {
        Assert.notNull(jpaItemWriter, "For art detail, An entity manager is required");
    }

}
