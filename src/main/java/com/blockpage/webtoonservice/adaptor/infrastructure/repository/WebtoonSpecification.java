package com.blockpage.webtoonservice.adaptor.infrastructure.repository;

import com.blockpage.webtoonservice.adaptor.infrastructure.entity.WebtoonEntity;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import net.bytebuddy.implementation.Implementation.SpecialMethodInvocation;
import org.springframework.data.jpa.domain.Specification;

public class WebtoonSpecification {

    public static Specification<WebtoonEntity> findByCreator(String keyword) {
        return new Specification<WebtoonEntity>() {
            @Override
            public Predicate toPredicate(Root<WebtoonEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("creator"), "%" + keyword + "%");
            }
        };
    }

    public static Specification<WebtoonEntity> containingTitle(String keyword) {
        return new Specification<WebtoonEntity>() {
            @Override
            public Predicate toPredicate(Root<WebtoonEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like(root.get("webtoonTitle"), "%" + keyword + "%");
            }
        };
    }

}
