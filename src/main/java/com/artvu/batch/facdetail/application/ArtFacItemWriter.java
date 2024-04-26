package com.artvu.batch.facdetail.application;

import com.artvu.batch.artdetail.application.ArtDetailService;
import com.artvu.batch.facdetail.domain.entity.KopisFacDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ArtFacItemWriter<T> extends JpaItemWriter<List<KopisFacDetail>> {

    private JpaItemWriter<KopisFacDetail> jpaItemWriter;

    @Autowired
    private ArtDetailService artDetailService;

    public ArtFacItemWriter(JpaItemWriter<KopisFacDetail> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(Chunk<? extends List<KopisFacDetail>> items) {

        log.info("art facility Detail WRITER ================================================");

        List<String> facIdList = artDetailService.artFacIdList();

        Chunk<KopisFacDetail> collect = new Chunk<>();
        for(List<KopisFacDetail> list : items){

            List<KopisFacDetail> newList = new ArrayList<>();
            for (KopisFacDetail facDetail : list) {
                if ( !facIdList.contains(facDetail.getArtFacId())) {
                    newList.add(facDetail);
                }
            }
           collect.addAll(newList);
        }
        jpaItemWriter.write(collect);

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(jpaItemWriter, "For facility, An entity manager is required");
    }

}
