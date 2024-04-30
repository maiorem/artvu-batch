package com.artvu.batch.artvu.infrastructure.repository;

import com.artvu.batch.artvu.domain.entity.ArtList;
import com.artvu.batch.artvu.domain.entity.ArtTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtTimeRepository extends JpaRepository<ArtTime, Integer> {
    Optional<ArtTime> findByArtlist(ArtList artList);
}
