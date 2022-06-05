package com.alkemychallenge.alkemychallenge.entity;

import com.alkemychallenge.alkemychallenge.dto.MovieSerieDto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter
@SQLDelete(sql= "UPDATE characters SET deleted=true WHERE id=?")
@Where(clause="deleted=false")
public class CharacterEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE)
        private Long id;
        private String imageCharacter;
        private String nameCharacter;
        private Integer age;
        private Float weight;
        private String history;
        @ManyToMany(mappedBy = "characters")//, cascade = CascadeType.ALL)
        private List<MovieSerieEntity> movieSeries = new ArrayList<>();
        private boolean deleted= Boolean.FALSE;

        public void addMovieSerie(MovieSerieEntity movieSerie){
                movieSeries.add(movieSerie);
        }
        public void removeMovieSerie(MovieSerieDto movieSerie){
                movieSeries.remove(movieSerie);
        }
}
