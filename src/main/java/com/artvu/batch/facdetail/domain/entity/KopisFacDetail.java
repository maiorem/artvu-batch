package com.artvu.batch.facdetail.domain.entity;

import com.artvu.batch.common.entity.BaseRegDate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "TB_KOPIS_FAC_DETAIL")
@Comment("[KOPIS] 공연시설상세내용")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@IdClass(ArtFacId.class)
public class KopisFacDetail extends BaseRegDate {

    @Id
    @Column(length = 20, name = "ART_FAC_ID")
    @Comment("공연시설ID")
    private String artFacId;

    @Id
    @Column(name = "REG_DT")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Comment("수집일시")
    private LocalDateTime regDt;

    @Column(length = 200, name = "ART_FAC_NM")
    @Comment("공연시설명")
    private String artFacNm;

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
    private String store24Yn;

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
    @Comment("장애시설_엘리베이터_여부")
    private String disPersonElevatorYn;

    @Column(length = 1, name = "PARKING_YN")
    @Comment("주차시설여부")
    private String parkingYn;

}
