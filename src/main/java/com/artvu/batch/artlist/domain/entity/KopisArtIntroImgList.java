package com.artvu.batch.artlist.domain.entity;

import com.artvu.batch.common.entity.BaseRegDate;
import com.artvu.batch.artdetail.domain.entity.KopisArtDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@Builder
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Comment("[KOPIS] 공연소개이미지목록")
@Table(name = "TB_KOPIS_ART_INTRO_IMG_LIST")
public class KopisArtIntroImgList extends BaseRegDate {

    @Id
    @Column(name = "ART_INTRO_IMG_ID")
    @Comment("공연 소개 이미지 ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artIntroImgId;

    @JoinColumn(name = "ART_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    @Comment("공연ID")
    private KopisArtDetail artDetail;

    @Column(length = 2000, name = "INTRODUCT_IMG_URL")
    @Comment("소개이미지URL")
    private String introductImgUrl;

}
