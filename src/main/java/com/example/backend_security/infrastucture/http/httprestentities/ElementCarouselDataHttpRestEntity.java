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
    private Integer position;
    private ElementCarouselHttpRestEntity elementCarousel;

    public ElementCarouselData mapToDomain() {
        ElementCarouselData elementCarouselData = new ElementCarouselData(this.getId(),
                this.getType(), this.getTitle(), this.getPosition(), this.getElementCarousel().mapToDomainReduced());
        return elementCarouselData;
    }

}
