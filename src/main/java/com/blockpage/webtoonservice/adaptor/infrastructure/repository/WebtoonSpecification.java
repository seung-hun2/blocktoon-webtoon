package com.blockpage.webtoonservice.adaptor.infrastructure.repository;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import org.springframework.data.jpa.domain.Specification;

public class WebtoonSpecification {

    public static Specification<WebtoonEntity> equalCreator(String creator){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("creator"), "%"+creator+"%"));
    }

    public static Specification<WebtoonEntity> equalIllustrator(String illustrator){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("illustrator"), "%"+illustrator+"%"));
    }

    public static Specification<WebtoonEntity> equalTitle(String title){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("webtoonTitle"), "%"+title+"%"));
    }
}
