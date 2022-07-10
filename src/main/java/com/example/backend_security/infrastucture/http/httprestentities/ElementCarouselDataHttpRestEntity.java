package com.example.backend_security.infrastucture.http.httprestentities;

import com.example.backend_security.domain.entities.ElementCarouselData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementCarouselDataHttpRestEntity {

    private Long id;
    private String type;
    private String title;
    private ElementCarouselHttpRestEntity elementCarousel;

    public ElementCarouselData mapToDomain(ElementCarouselDataHttpRestEntity elementCarouselDataHttpRestEntity) {
        ElementCarouselData elementCarouselData = new ElementCarouselData(elementCarouselDataHttpRestEntity.getId(),
                elementCarouselDataHttpRestEntity.getType(), elementCarouselDataHttpRestEntity.getTitle(),
                elementCarouselDataHttpRestEntity.getElementCarousel()
                        .mapToDomainReduced(elementCarouselDataHttpRestEntity.getElementCarousel()));
        return elementCarouselData;
    }

}
