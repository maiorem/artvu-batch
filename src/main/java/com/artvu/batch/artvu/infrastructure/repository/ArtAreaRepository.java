package com.artvu.batch.artvu.infrastructure.repository;

import com.artvu.batch.artvu.domain.entity.ArtArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtAreaRepository extends JpaRepository<ArtArea, String> {
    ArtArea findByAreaNm(String areaNm);
}
