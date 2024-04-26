package com.artvu.batch.artlist.domain.entity;

import com.artvu.batch.common.entity.BaseRegDate;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter @Setter
@Entity
@Table(name = "TB_KOPIS_ART_LIST")
@Comment("[KOPIS] 공연목록")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class KopisArtList extends BaseRegDate {

    @Id
    @Column(length = 20, name = "ART_ID")
    @Comment("공연ID")
    private String artId;

    @Column(length = 200, name = "ART_NM")
    @Comment("공연명")
    private String artNm;

    @Column(length = 10, name = "ART_STR_DT")
    @Comment("공연시작일")
    private String artStrDt;

    @Column(length = 10, name = "ART_END_DT")
    @Comment("공연종료일")
    private String artEndDt;

    @Column(length = 45, name = "ART_FAC_NM")
    @Comment("공연시설명")
    private String artFacNm;

    @Column(length = 500, name = "POSTER_IMG_URL")
    @Comment("포스터이미지경로")
    private String posterImgUrl;

    @Column(length = 20, name = "ART_AREA_NM")
    @Comment("공연지역명")
    private String artAreaNm;

    @Column(length = 20, name = "GENRE_NM")
    @Comment("장르")
    private String genreNm;

    @Column(length = 10, name = "STATUS")
    @Comment("공연상태")
    private String status;

    @Column(length = 1, name = "OPENRUN_YN")
    @Comment("오픈런여부")
    private String openrunYn;

}
