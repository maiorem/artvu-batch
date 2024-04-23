package com.artvu.batch.artvu.domain.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Comment("상품상세내용")
@Table(name = "TB_ART_DETAIL")
public class ArtDetail {

    @Id
    @Column(length = 20, name = "ART_ID")
    @Comment("공연ID")
    private String artId;


    @Column(length = 200, name = "ART_STAFF")
    @Comment("공연제작진")
    private String artStaff;

    @Column(length = 200, name = "ART_ACTOR")
    @Comment("공연출연진")
    private String artActor;

    @Column(length = 50, name = "ART_RUNTIME")
    @Comment("런타임")
    private String artRuntime;


    @Column(length = 100, name = "PROD_COMP_NM")
    @Comment("제작사")
    private String prodCompNm;

    @Column(length = 100, name = "AGENCY_NM")
    @Comment("기획사")
    private String agencyNm;

    @Column(length = 100, name = "SPONSOR_NM")
    @Comment("주최")
    private String sponsorNm;

    @Column(length = 100, name = "ORGANIZER_NM")
    @Comment("주관")
    private String organizerNm;

    @Column(length = 4000, name = "SUMMARY")
    @Comment("줄거리")
    private String summary;

    @Column(length = 4000, name = "ART_CONT_DETAIL")
    @Comment("상세내용")
    private String contDetail;

    @Column(length = 1, name = "INTERMIS_YN")
    @Comment("인터미션여부")
    private String intermisYn;

    @Column(name = "INTERMIS_MI")
    @Comment("인터미션시간(분)")
    private int intermisMi;

    @Column(length = 1, name = "OPENRUN_YN")
    @Comment("오픈런여부")
    private String openrunYn;

    @Column(length = 1, name = "VISIT_KOR_YN")
    @Comment("내한여부")
    private String visitKorYn;

    @Column(length = 1, name = "CHILD_YN")
    @Comment("아동극여부")
    private String childYn;

    @Column(length = 1, name = "DAEHAKRO_YN")
    @Comment("대학로여부")
    private String daehakroYn;

    @Column(length = 1, name = "FESTIVAL_YN")
    @Comment("축제여부")
    private String festivalYn;

    @Column(length = 1, name = "MUSICAL_LICENSE_YN")
    @Comment("뮤지컬라이선스여부")
    private String musicalLicenseYn;

    @Column(length = 1, name = "MUSICAL_CREATE_YN")
    @Comment("뮤지컬창작여부")
    private String musicalCreateYn;

    @Column(name = "LAST_MOD_DT")
    @Comment("최종수정일")
    private String lastModDt;


}
