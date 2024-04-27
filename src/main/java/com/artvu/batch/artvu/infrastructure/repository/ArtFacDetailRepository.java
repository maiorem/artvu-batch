package com.artvu.batch.artvu.infrastructure.repository;

import com.artvu.batch.artvu.domain.entity.ArtFacDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtFacDetailRepository extends JpaRepository<ArtFacDetail, String> {
    ArtFacDetail findByArtFacId(String facId);
}
