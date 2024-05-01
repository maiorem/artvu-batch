package com.artvu.batch.artdetail.infrastructure.repository;

import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KopisArtDetailApiRepository extends JpaRepository<KopisArtDetail, String> {
    KopisArtDetail findByArtId(String artId);
}
