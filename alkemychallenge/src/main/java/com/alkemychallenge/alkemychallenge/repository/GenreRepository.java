package com.alkemychallenge.alkemychallenge.repository;

import com.alkemychallenge.alkemychallenge.entity.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Long> {

}
