package com.artvu.batch.bestprice.infrastructure.repository;

import com.artvu.batch.bestprice.domain.entity.KopisCrawlArtCont;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrawlArtContRepository extends JpaRepository<KopisCrawlArtCont, Integer> {
    Optional<KopisCrawlArtCont> findByArtNm(String artNm);
}
