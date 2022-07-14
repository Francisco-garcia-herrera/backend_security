package com.example.backend_security.domain.entities;

import java.util.ArrayList;
import java.util.List;

import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaElementCarousel;
import com.example.backend_security.infrastucture.database.entities.JpaElementCarouselData;
import com.example.backend_security.infrastucture.http.httprestentities.ElementCarouselDataHttpRestEntity;
import com.example.backend_security.infrastucture.http.httprestentities.ElementCarouselHttpRestEntity;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;

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
public class ElementCarousel extends Element {

    private String type;
    private String title;
    private List<ElementCarouselData> elementCarouselDatas;

    public ElementCarousel(Integer position, String type, String title,
            List<ElementCarouselData> elementCarouselDatas) {
        super(position);
        this.type = type;
        this.title = title;
        this.elementCarouselDatas = elementCarouselDatas;
    }

    public ElementCarousel(Long id, Integer position, Page page, String type, String title,
            List<ElementCarouselData> elementCarouselDatas) {
        super(id, position, page);
        this.type = type;
        this.title = title;
        this.elementCarouselDatas = elementCarouselDatas;
    }

    public String render() {
        return "Elemento Carousel: " + this.getPosition() + ". Title: " + this.title;
    }

    public ElementHttpRestEntity mapToDto() {

        List<ElementCarouselDataHttpRestEntity> elementCarouselDatas = new ArrayList<>();
        if (this.elementCarouselDatas != null) {
            for (ElementCarouselData elementCarouselData : this.elementCarouselDatas) {
                ElementCarouselDataHttpRestEntity elementCarouselDataHttpRestEntity = new ElementCarouselDataHttpRestEntity(
                        elementCarouselData.getId(), elementCarouselData.getType(), elementCarouselData.getTitle(),
                        elementCarouselData.getElementCarousel()
                                .mapToElementCarrouselDto());
                elementCarouselDatas.add(elementCarouselDataHttpRestEntity);
            }
        }

        ElementHttpRestEntity elementHttpRestEntity = new ElementCarouselHttpRestEntity(this.getId(),
                this.getPosition(), this.getPage().mapToDtoReduced(), this.type, this.title,
                elementCarouselDatas);
        return elementHttpRestEntity;
    }

    public JpaElement mapToJpa() {
        List<JpaElementCarouselData> jpaElementCarouselDatas = new ArrayList<>();
        if (this.getElementCarouselDatas() != null) {
            for (ElementCarouselData elementCarouselData : this.getElementCarouselDatas()) {
                JpaElementCarouselData jpaElementCarouselData = new JpaElementCarouselData(elementCarouselData.getId(),
                        elementCarouselData.getType(), elementCarouselData.getTitle(),
                        elementCarouselData.getElementCarousel()
                                .mapToJpaElementCarrousel());
                jpaElementCarouselDatas.add(jpaElementCarouselData);
            }
        }

        JpaElement jpaElement = new JpaElementCarousel(this.getId(), this.getPosition(),
                this.getPage().mapToJpa(), this.getType(), this.getTitle(), jpaElementCarouselDatas);
        return jpaElement;
    }

    public ElementCarouselHttpRestEntity mapToElementCarrouselDto() {
        ElementCarouselHttpRestEntity elementCarouselHttpRestEntity = new ElementCarouselHttpRestEntity();
        elementCarouselHttpRestEntity.setId(this.getId());
        return elementCarouselHttpRestEntity;
    }

    public JpaElementCarousel mapToJpaElementCarrousel() {
        JpaElementCarousel jpaElementCarousel = new JpaElementCarousel();
        jpaElementCarousel.setId(this.getId());
        return jpaElementCarousel;
    }
}
