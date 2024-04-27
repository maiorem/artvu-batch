package com.artvu.batch.artvu.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDate;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Comment("시간")
@Table(name = "TB_ART_TIME")
public class ArtTime {

    @Id
    @Column(name = "ART_TIME_ID")
    @Comment("공연시간ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int artTimeId;

    @Column(length = 10, name = "ART_STR_DT")
    @Comment("공연시작일시")
    private String artStrDt;

    @Column(length = 10, name = "ART_END_DT")
    @Comment("공연종료일시")
    private String artEndDt;

    @Column(length = 500, name = "ART_OPEN_TIME")
    @Comment("공연일시")
    private String artTime;

    @Column(length = 100, name = "REG_DT")
    @Comment("DB 등록시간")
    private LocalDate regDt;

    @JoinColumn(name = "ART_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private ArtList artlist;


}
