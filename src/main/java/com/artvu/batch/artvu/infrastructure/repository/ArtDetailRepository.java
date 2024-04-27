package com.artvu.batch.artvu.infrastructure.repository;

import com.artvu.batch.artvu.domain.entity.ArtDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtDetailRepository extends JpaRepository<ArtDetail, String> {
}
