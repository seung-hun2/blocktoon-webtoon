package com.blockpage.webtoonservice.adaptor.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WebtoonRepository extends JpaRepository<WebtoonEntity, Long> {
    List<WebtoonEntity> findByGenreTypeAndWebtoonStatus(String genre, int status);


}
