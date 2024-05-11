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
@Comment("지역코드 : 법정동코드를 사용함.")
@Table(name = "TB_ART_AREA")
public class ArtArea {

    @Id
    @Column(length = 20, name = "AREA_CODE")
    @Comment("지역코드")
    private String areaCode;


    @Column(length = 100, name = "AREA_NM")
    @Comment("지역명")
    private String areaNm;

    @Column(name = "AREA_ORD_NO")
    @Comment("지역순서")
    private int areaOrdNo;

}
