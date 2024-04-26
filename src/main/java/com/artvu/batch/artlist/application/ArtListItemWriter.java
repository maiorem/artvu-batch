package com.artvu.batch.artlist.application;

import com.artvu.batch.artlist.domain.entity.KopisArtList;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ArtListItemWriter<T> extends JpaItemWriter<List<KopisArtList>> {

    private JpaItemWriter<KopisArtList> jpaItemWriter;

    @Autowired
    private ArtListService artService;

    public ArtListItemWriter(JpaItemWriter<KopisArtList> jpaItemWriter) {
        this.jpaItemWriter = jpaItemWriter;
    }

    @Override
    @Transactional
    public void write(Chunk<? extends List<KopisArtList>> items) {
        log.info("art LIST WRITER ================================================");

        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy.MM.dd");

        List<String> artIdList = artService.artIdList();

        Chunk<KopisArtList> collect = new Chunk<>();
        for(List<KopisArtList> list : items){
            List<KopisArtList> newList = new ArrayList<>();
            for (KopisArtList kopisArtList : list) {
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
        Assert.notNull(jpaItemWriter, "For art list, An entity manager is required");
    }
}
