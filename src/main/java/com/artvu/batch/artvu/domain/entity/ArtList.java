package com.artvu.batch.artvu.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Builder
@Table(name = "TB_ART")
@Comment("상품목록")
@NoArgsConstructor
@AllArgsConstructor
public class ArtList {

    @Id
    @Column(length = 20, name="ART_ID")
    private String artId;

    @Column(length = 200, name = "ART_NM")
    @Comment("공연명")
    private String artNm;

    @Comment("공연시설ID")
    @JoinColumn(name = "ART_FAC_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private ArtFacDetail artFacId;

    @Column(name = "ORG_PRICE")
    @Comment("원본가격")
    @ColumnDefault("0")
    private int orgPrice;

    @Column(name = "MIN_PRICE")
    @Comment("최저가격")
    @ColumnDefault("0")
    private int minPrice;

    @Column(name = "MIN_PRICE_REG_DT")
    @Comment("최저가격기준시간")
    private LocalDateTime minPriceRegDt;

    @Column(length = 10, name = "ART_STATUS")
    @Comment("공연상태")
    private String status;

    @Column(length = 2000, name = "COPY_TEXT")
    @Comment("카피라이터")
    private String copyText;

    @Column(length = 50, name = "ART_SHOW_AGE")
    @Comment("공연관람연령")
    private String artShowAge;

    @Builder.Default
    @OneToMany(mappedBy = "artList")
    private List<ArtGenreMppg> artGenreMppgs = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "artList")
    private List<ArtImg> artImgList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AREA_CODE")
    @Comment("지역정보-지역코드")
    private ArtArea areaCode;

}
