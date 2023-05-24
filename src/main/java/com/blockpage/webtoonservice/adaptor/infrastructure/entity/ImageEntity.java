package com.blockpage.webtoonservice.adaptor.infrastructure.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "image")
public class ImageEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long webtoonId;
    @Column
    private Integer episodeNumber;
    @Column
    private Boolean erase;
    @Column
    private String image;
    @Column
    private Integer imageNumber;

    public static ImageEntity toEntity(Long webtoonId, Integer episodeNumber, Integer imageNumber, String image) {
        return ImageEntity.builder()
            .webtoonId(webtoonId)
            .episodeNumber(episodeNumber)
            .imageNumber(imageNumber)
            .image(image)
            .erase(Boolean.FALSE)
            .build();
    }

}
