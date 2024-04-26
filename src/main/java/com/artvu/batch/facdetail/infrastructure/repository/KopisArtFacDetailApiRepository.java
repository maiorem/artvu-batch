package com.artvu.batch.facdetail.infrastructure.repository;

import com.artvu.batch.facdetail.domain.entity.KopisFacDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KopisArtFacDetailApiRepository extends JpaRepository<KopisFacDetail, String> {
}
