package com.artvu.batch.bestprice.domain.entity;

import com.artvu.batch.common.entity.BaseRegDate;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@Setter
@Entity
@Table(name = "TB_CRAW_ART_CONT")
@Comment("[인터파크 크롤링] 공연 최저가 정보")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class KopisCrawlArtCont extends BaseRegDate {

    @Id
    @Column(name = "ART_CRAWL_ID")
    @Comment("크롤링Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artCrawlId;

    @Column(length = 45, name = "ART_NM")
    @Comment("공연명")
    private String artNm;


    @Column(name = "ART_MIN_PRICE")
    @Comment("최저가")
    private int artMinPrice;


    @Column(length = 20, name = "ART_WEB_NM")
    @Comment("수집 사이트")
    private String artWebNm;

}
