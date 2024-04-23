package com.artvu.batch.artvu.domain.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Comment("상품장르연결내역")
@Table(name = "TB_ART_GENRE_MPPG")
public class ArtGenreMppg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ART_GENRE_MPPG_ID")
    @Comment("장르매핑ID")
    private int artGenreMppgId;

    @ManyToOne
    @JoinColumn(name = "ART_ID")
    @Comment("상품_상품ID")
    private ArtList artList;

    @ManyToOne
    @JoinColumn(name = "ART_GENRE_ID")
    @Comment("장르_장르번호")
    private GenreList genreList;

}
