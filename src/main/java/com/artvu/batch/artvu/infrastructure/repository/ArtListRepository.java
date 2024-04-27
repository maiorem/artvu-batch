package com.artvu.batch.artvu.infrastructure.repository;

import com.artvu.batch.artvu.domain.entity.ArtList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtListRepository extends JpaRepository<ArtList, String> {
}
