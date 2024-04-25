package com.artvu.batch.facdetail.application;

import com.artvu.batch.artdetail.application.ArtDetailService;
import com.artvu.batch.facdetail.domain.entity.KopisFacDetail;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ArtFacItemWriter<T> extends JpaItemWriter<List<KopisFacDetail>> {

    private JpaItemWriter<List<KopisFacDetail>> jpaItemWriter;

    @Autowired
    private ArtDetailService artDetailService;

    public ArtFacItemWriter(JpaItemWriter<List<KopisFacDetail>> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    public void write(Chunk<? extends List<KopisFacDetail>> items) {
        List<String> facIdList = artDetailService.artFacIdList();

        List<List<KopisFacDetail>> allList = new ArrayList<>();
        Chunk<List<KopisFacDetail>> collect = new Chunk<>();
        for(List<KopisFacDetail> list : items){

            List<KopisFacDetail> newList = new ArrayList<>();
            for (KopisFacDetail facDetail : list) {
                if ( !facIdList.contains(facDetail.getArtFacId())) {
                    newList.add(facDetail);
                }
                allList.add(newList);
            }
           collect.addAll(allList);
        }
        jpaItemWriter.write(collect);

    }


    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
    }

}
