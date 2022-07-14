package com.example.backend_security.domain.entities;

import com.example.backend_security.infrastucture.http.httprestentities.ElementCarouselDataHttpRestEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ElementCarouselData {
    private Long id;
    private String type;
    private String title;
    private ElementCarousel elementCarousel;

    public ElementCarouselDataHttpRestEntity mapToDto() {
        ElementCarouselDataHttpRestEntity elementCarouselData = new ElementCarouselDataHttpRestEntity(this.getId(),
                this.getType(), this.getTitle(),
                this.getElementCarousel().mapToElementCarrouselDto());
        return elementCarouselData;
    }
}
