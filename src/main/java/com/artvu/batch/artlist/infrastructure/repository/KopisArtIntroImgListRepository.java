package com.artvu.batch.artlist.infrastructure.repository;

import com.artvu.batch.artlist.domain.entity.KopisArtIntroImgList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KopisArtIntroImgListRepository extends JpaRepository<KopisArtIntroImgList, String> {
}
