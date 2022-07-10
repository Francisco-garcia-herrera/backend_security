package com.example.backend_security.infrastucture.http.httprestentities;

import java.util.ArrayList;
import java.util.List;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementCarousel;
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
public class ElementCarouselHttpRestEntity extends ElementHttpRestEntity {

    private String type;

    private String title;

    private List<ElementCarouselDataHttpRestEntity> elementCarouselDatas;

    @Override
    public String getType() {
        return this.type;
    }

    public ElementCarouselHttpRestEntity(Integer position, PageHttpRestEntity page, String type, String title,
            List<ElementCarouselDataHttpRestEntity> elementCarouselDatas) {
        super(position, page);
        this.type = type;
        this.title = title;
        this.elementCarouselDatas = elementCarouselDatas;
    }

    public ElementCarouselHttpRestEntity(Long id, Integer position, PageHttpRestEntity page, String type,
            String title, List<ElementCarouselDataHttpRestEntity> elementCarouselDatas) {
        super(id, position, page);
        this.type = type;
        this.title = title;
        this.elementCarouselDatas = elementCarouselDatas;
    }

    public Element mapToDomain(ElementHttpRestEntity elementHttpRestEntity) {
        List<ElementCarouselData> elementCarouselDatas = new ArrayList<>();
        if (this.getElementCarouselDatas() != null) {

            for (ElementCarouselDataHttpRestEntity elementCarouselDataHttpRestEntity : this.getElementCarouselDatas()) {

                ElementCarousel elementCarousel = new ElementCarousel();

                if (elementCarouselDataHttpRestEntity.getElementCarousel() != null) {
                    elementCarousel.setId(elementCarouselDataHttpRestEntity.getId());
                }

                ElementCarouselData elementCarouselData = new ElementCarouselData(
                        elementCarouselDataHttpRestEntity.getId(), elementCarouselDataHttpRestEntity.getType(),
                        elementCarouselDataHttpRestEntity.getTitle(),
                        elementCarousel);
                elementCarouselDatas.add(elementCarouselData);
            }
        }

        Element element = new ElementCarousel(elementHttpRestEntity.getId(), elementHttpRestEntity.getPosition(),
                elementHttpRestEntity.getPage().mapToDomain(elementHttpRestEntity.getPage()),
                this.type, this.title, elementCarouselDatas);
        return element;
    }

    public ElementCarousel mapToDomainReduced(ElementCarouselHttpRestEntity elementCarouselHttpRestEntity) {
        ElementCarousel elementCarousel = new ElementCarousel();
        if (elementCarouselHttpRestEntity.getId() != null) {
            elementCarousel.setId(elementCarouselHttpRestEntity.getId());
        }
        return elementCarousel;
    }

}
