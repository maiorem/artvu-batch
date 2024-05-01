package com.artvu.batch.artvu.infrastructure.repository;

import com.artvu.batch.artvu.domain.entity.ArtList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtListRepository extends JpaRepository<ArtList, String> {
    Optional<ArtList> findByArtId(String artId);
}
