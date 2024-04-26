package com.artvu.batch.artdetail.domain.entity;

import com.artvu.batch.common.entity.BaseRegDate;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@ToString
@Entity
@Table(name = "TB_KOPIS_ART_DETAIL")
@Comment("[KOPIS] 공연상세내용")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class KopisArtDetail extends BaseRegDate {

    @Id
    @Column(length = 20, name = "ART_ID")
    @Comment("공연ID")
    private String artId;

    @Column(length = 20, name = "ART_FAC_ID")
    @Comment("공연시설ID")
    private String artFacId;

    @Column(length = 200, name = "ART_NM")
    @Comment("공연명")
    private String artNm;

    @Column(length = 10, name = "ART_STR_DT")
    @Comment("공연시작일")
    private String artStrDt;

    @Column(length = 10, name = "ART_END_DT")
    @Comment("공연종료일")
    private String artEndDt;

    @Column(length = 200, name = "ART_FAC_NM")
    @Comment("공연시설명")
    private String artFacNm;

    @Column(length = 200, name = "ART_ACTORS")
    @Comment("공연출연진")
    private String artActors;

    @Column(length = 200, name = "ART_STAFF")
    @Comment("공연제작진")
    private String artStaff;

    @Column(length = 50, name = "ART_RUNTIME")
    @Comment("런타임")
    private String artRuntime;

    @Column(length = 50, name = "ART_SHOW_AGE")
    @Comment("공연관람연령")
    private String artShowAge;

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

    @Column(length = 200, name = "PRICE")
    @Comment("티켓가격")
    private String price;

    @Column(length = 500, name = "POSTER_IMG_URL")
    @Comment("포스터이미지경로")
    private String posterImgUrl;

    @Column(length = 4000, name = "SUMMARY")
    @Comment("줄거리")
    private String summary;

    @Column(length = 20, name = "GENRE_NM")
    @Comment("장르")
    private String genreNm;

    @Column(length = 10, name = "STATUS")
    @Comment("공연상태")
    private String status;

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

    @Column(length = 500, name = "ART_TIME")
    @Comment("공연시각")
    private String artTime;


}
