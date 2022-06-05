package com.alkemychallenge.alkemychallenge.repository.specifications;

import com.alkemychallenge.alkemychallenge.dto.CharacterFiltersDto;
import com.alkemychallenge.alkemychallenge.entity.CharacterEntity;
import com.alkemychallenge.alkemychallenge.entity.MovieSerieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

@Component
public class CharacterSpecification {
    public Specification<CharacterEntity> getByFilters(CharacterFiltersDto filtersDto) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDto.getNameCharacter())) {
                predicates.add((Predicate) criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + filtersDto.getNameCharacter().toLowerCase() + "%"));
            }
            /*if (StringUtils.hasLength(filtersDto.getAge())) {
                predicates.add((Predicate) criteriaBuilder.like(criteriaBuilder(root.get("age")), "%" + filtersDto.getAge() + "%"));

            }*/
            if (!CollectionUtils.isEmpty(filtersDto.getMovieSeries())) {
                Join<MovieSerieEntity, CharacterEntity> join = root.join("movieSeries", JoinType.INNER);
                Expression<String> movieseriesId = join.get("id");
                predicates.add((Predicate) movieseriesId.in(filtersDto.getMovieSeries()));
            }
            //remove Duplicates
            query.distinct(true);

            //Order Resolver
            String orderByField = "denomination";
            query.orderBy(
                    filtersDto.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );
            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));

        };

    }
}

