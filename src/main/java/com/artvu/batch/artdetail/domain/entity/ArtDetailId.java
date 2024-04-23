package com.artvu.batch.artdetail.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtDetailId implements Serializable {

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "ART_ID")
    private String artId;

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "ART_FAC_ID")
    private String artFacId;

    @EqualsAndHashCode.Include
    @Id
    @Column(name = "REG_DT")
    private LocalDateTime regDt;
}
