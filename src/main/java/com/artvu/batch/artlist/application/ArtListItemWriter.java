package com.artvu.batch.artlist.application;

import com.artvu.batch.artlist.domain.entity.KopisArtList;
import com.artvu.batch.artlist.infrastructure.repository.KopisArtListApiRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ArtListItemWriter<T> extends JpaItemWriter<List<com.artvu.batch.artlist.domain.entity.KopisArtList>> {

    private final JpaItemWriter<com.artvu.batch.artlist.domain.entity.KopisArtList> jpaItemWriter;

    @Autowired
    private KopisArtListApiRepository repository;

    public ArtListItemWriter(JpaItemWriter<com.artvu.batch.artlist.domain.entity.KopisArtList> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    @Transactional
    public void write(Chunk<? extends List<com.artvu.batch.artlist.domain.entity.KopisArtList>> items) {
        log.info("art LIST WRITER ================================================");

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        List<com.artvu.batch.artlist.domain.entity.KopisArtList> dbAll = repository.findAll();
        List<String> artIdList = new ArrayList<>();
        for (com.artvu.batch.artlist.domain.entity.KopisArtList kopisArtList : dbAll) {
            artIdList.add(kopisArtList.getArtId());
        }

        Chunk<com.artvu.batch.artlist.domain.entity.KopisArtList> collect = new Chunk<>();
        for(List<com.artvu.batch.artlist.domain.entity.KopisArtList> list : items){
            List<KopisArtList> newList = new ArrayList<>();
            for (com.artvu.batch.artlist.domain.entity.KopisArtList kopisArtList : list) {
                if (!artIdList.contains(kopisArtList.getArtId())) {
                    newList.add(kopisArtList);
                } else {
                    if (today.isAfter(LocalDate.parse(kopisArtList.getArtStrDt(), dateFormat) )) {
                        kopisArtList.setStatus("공연중");
                    }
                    if ( today.isAfter(LocalDate.parse(kopisArtList.getArtEndDt(), dateFormat) ) ) {
                        kopisArtList.setStatus("공연종료");
                    }
                }
            }
            collect.addAll(newList);
        }
        jpaItemWriter.write(collect);
    }

    @Override
    public void afterPropertiesSet()  {

    }
}
