package com.artvu.batch.bestprice.application;

import com.artvu.batch.bestprice.domain.entity.KopisCrawlArtCont;
import com.artvu.batch.bestprice.infrastructure.repository.CrawlArtContRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CrawlDataService {

    private final CrawlArtContRepository crawlArtContRepository;

    public Optional<KopisCrawlArtCont> findByArtNm(String artNm){
        return crawlArtContRepository.findByArtNm(artNm);
    }

}
