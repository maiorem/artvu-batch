package com.artvu.batch.artlist.infrastructure.repository;

import com.artvu.batch.artlist.domain.entity.KopisArtList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KopisArtListApiRepository extends JpaRepository<KopisArtList, String> {
}