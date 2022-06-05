package com.alkemychallenge.alkemychallenge.service.implementacion;

import com.alkemychallenge.alkemychallenge.dto.GenreDto;
import com.alkemychallenge.alkemychallenge.entity.GenreEntity;
import com.alkemychallenge.alkemychallenge.mapper.GenreMapper;
import com.alkemychallenge.alkemychallenge.repository.GenreRepository;
import com.alkemychallenge.alkemychallenge.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;

    @Autowired
    private GenreRepository genreRepository;

    public GenreDto save(GenreDto dto){
        GenreEntity entity = genreMapper.genreDto2Entity(dto);
        GenreEntity entitySave = genreRepository.save(entity);
        GenreDto result = genreMapper.genreEntity2Dto((entitySave));
        return result;
    }

    public List<GenreDto> getAllGenres() {
        List<GenreEntity> entities = genreRepository.findAll();
        List<GenreDto> result = genreMapper.genreEntityList2DtoList(entities);
        return result;
    }
}
