package com.alkemychallenge.alkemychallenge.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movie_series")
@Getter
@Setter
@SQLDelete(sql= "UPDATE movie_series SET deleted=true WHERE id=?")
@Where(clause="deleted=false")
public class MovieSerieEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;
        private String imageMovieSerie;
        private String title;

        @Column(name="creation_date")
        @DateTimeFormat(pattern = "yyyy/mm/dd")
        private LocalDate creationDate;
        private Integer rating;

        @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinColumn(name = "id_genre", insertable = false, updatable = false)
        private GenreEntity genreEntity;

        @Column(name = "id_genre", nullable = false)
        private Long idGenre;

        @ManyToMany(
                cascade = {
                        CascadeType.PERSIST,
                        CascadeType.MERGE
                })
        @JoinTable(
                name = "character_movie_serie",
                joinColumns = @JoinColumn(name = "id_movie_serie"),
                inverseJoinColumns = @JoinColumn(name = "id_character")
        )
        private Set<CharacterEntity> characters = new HashSet<>();

        private boolean deleted= Boolean.FALSE;
}
