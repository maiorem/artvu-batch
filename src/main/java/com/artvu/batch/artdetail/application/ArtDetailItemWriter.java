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

    private final JpaItemWriter<List<KopisArtDetail>> jpaItemWriter;

    @Autowired
    private ArtListService artService;

    public ArtDetailItemWriter(JpaItemWriter<List<KopisArtDetail>> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(Chunk<? extends List<KopisArtDetail>> items) {
        log.info("art DETAIL WRITER ================================================");

        List<String> artIdList = artService.artIdList();
        Chunk<List<KopisArtDetail>> collectList = new Chunk<>();
        List<List<KopisArtDetail>> allList = new ArrayList<>();
        for(List<KopisArtDetail> list : items){
            List<KopisArtDetail> newList = new ArrayList<>();
            for (KopisArtDetail kopisArtDetail : list) {
                if ( !artIdList.contains(kopisArtDetail.getArtId())) {
                    newList.add(kopisArtDetail);
                }
                allList.add(newList);
            }
            collectList.addAll(allList);
        }

        jpaItemWriter.write(collectList);

    }

    @Override
    public void afterPropertiesSet()  {
        Assert.notNull(jpaItemWriter, "An entity manager is required");
    }

}
