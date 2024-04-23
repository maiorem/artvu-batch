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
@Comment("시설정보")
@Table(name = "TB_ART_FAC_DETAIL")
public class ArtFacDetail {

    @Id
    @Column(length = 20, name = "ART_FAC_ID")
    @Comment("공연시설ID")
    private String artFacId;

    @Column(length = 200, name = "ART_FAC_NM")
    @Comment("공연시설명")
    private String artFacNm;

    @Column(length = 200, name = "CITY_NM")
    @Comment("시도명")
    private String cityNm;

    @Column(length = 200, name = "COUNTY_NM")
    @Comment("구군명")
    private String countyNm;

    @Column(name = "HALL_CNT")
    @Comment("공연장개수")
    private int hallCnt;

    @Column(length = 50, name = "ART_FAC_QUALITY")
    @Comment("공연시설특성")
    private String artFacQuality;

    @Column(length = 10, name = "OPEN_YY")
    @Comment("개관연도")
    private String openYy;

    @Column(name = "SEAT_CNT")
    @Comment("객석수")
    private int seatCnt;

    @Column(length = 20, name = "TEL_NO")
    @Comment("전화번호")
    private String telNo;

    @Column(length = 200, name = "SITE_URL")
    @Comment("홈페이지URL")
    private String siteUrl;

    @Column(length = 500, name = "ART_FAC_ADDR")
    @Comment("공연시설주소")
    private String artFacAddr;
    @Column(name = "LAT_NO")
    @Comment("위도")
    private Float latNo;

    @Column(name = "LON_NO")
    @Comment("경도")
    private Float lonNo;


    @Column(length = 1, name = "RESTAURANT_YN")
    @Comment("레스토랑여부")
    private String restrauntYn;

    @Column(length = 1, name = "CAFE_YN")
    @Comment("카페여부")
    private String cafeYn;

    @Column(length = 1, name = "STORE24_YN")
    @Comment("편의점여부")
    private String Store24Yn;

    @Column(length = 1, name = "PLAYROOM_YN")
    @Comment("놀이방여부")
    private String playroomYn;

    @Column(length = 1, name = "BABY_CARE_YN")
    @Comment("수유실여부")
    private String babyCareYn;

    @Column(length = 1, name = "DIS_PERSON_PARKIG_YN")
    @Comment("장애시설-주차장여부")
    private String disPersonParkingYn;

    @Column(length = 1, name = "DIS_PERSON_RESTRM_YN")
    @Comment("장애시설-화장실_여부")
    private String disPersonRestrmYn;

    @Column(length = 1, name = "DIS_PERSON_RUNWAY_YN")
    @Comment("장애시설-경사로_여부")
    private String disPersonRunwayYn;

    @Column(length = 1, name = "DIS_PERSON_ELEVATOR_YN")
    @Comment("애시설_엘리베이터_여부")
    private String disPersonElevatorYn;

    @Column(length = 1, name = "PARKING_YN")
    @Comment("주차시설여부")
    private String parkingYn;

    @Column(length = 2000, name = "SEAT_IMG_URL")
    @Comment("좌석이미지")
    private String seatImgUrl;
}
