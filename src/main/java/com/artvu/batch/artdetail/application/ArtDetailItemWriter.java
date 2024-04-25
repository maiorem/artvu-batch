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
    private ArtListService artService;

    public ArtDetailItemWriter(JpaItemWriter<KopisArtDetail> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(Chunk<? extends List<KopisArtDetail>> items) {
        log.info("art DETAIL WRITER ================================================");

        List<String> artIdList = artService.artIdList();

        for(List<KopisArtDetail> list : items){
            Chunk<KopisArtDetail> collect = new Chunk<>();
            List<KopisArtDetail> newList = new ArrayList<>();
            for (KopisArtDetail kopisArtDetail : list) {
                if ( !artIdList.contains(kopisArtDetail.getArtId())) {
                    newList.add(kopisArtDetail);
                }
                collect.addAll(newList);
            }
            jpaItemWriter.write(collect);
        }

    }

    @Override
    public void afterPropertiesSet()  {
        Assert.notNull(jpaItemWriter, "An entity manager is required");
    }

}
