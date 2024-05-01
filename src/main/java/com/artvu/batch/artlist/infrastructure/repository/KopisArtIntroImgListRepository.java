package com.artvu.batch.artlist.infrastructure.repository;

import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import com.artvu.batch.artlist.domain.entity.KopisArtIntroImgList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KopisArtIntroImgListRepository extends JpaRepository<KopisArtIntroImgList, String> {
    List<KopisArtIntroImgList> findAllByArtDetail(KopisArtDetail artDetail);
}
