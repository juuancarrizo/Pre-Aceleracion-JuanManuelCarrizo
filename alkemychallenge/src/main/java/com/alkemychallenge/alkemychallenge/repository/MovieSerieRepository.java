package com.alkemychallenge.alkemychallenge.repository;

import com.alkemychallenge.alkemychallenge.entity.MovieSerieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieSerieRepository extends JpaRepository<MovieSerieEntity,Long> {
}
