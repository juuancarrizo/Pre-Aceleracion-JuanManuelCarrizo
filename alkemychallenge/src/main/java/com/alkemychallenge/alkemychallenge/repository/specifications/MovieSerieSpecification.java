package com.alkemychallenge.alkemychallenge.repository.specifications;

import com.alkemychallenge.alkemychallenge.dto.MovieSerieFiltersDto;
import com.alkemychallenge.alkemychallenge.entity.MovieSerieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MovieSerieSpecification {
    public Specification<MovieSerieEntity> getByFilters(MovieSerieFiltersDto filtersDto){
        return(root, query, criteriaBuilder)-> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(filtersDto.getTitle())) {
                predicates.add((Predicate) criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + filtersDto.getTitle().toLowerCase() + "%"));
            }
            //remove Duplicates
            query.distinct(true);

            //Order Resolver
            String orderByField = "denomination";
            query.orderBy(
                    filtersDto.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField)));
            return criteriaBuilder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0]));

        };
    }
}

