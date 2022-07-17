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

    public ElementCarousel(Integer position, Page page, String type, String title,
            List<ElementCarouselData> elementCarouselDatas) {
        super(position, page);
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

        String carouselDatasText = "";
        int index = 0;
        for (ElementCarouselData elementCarouselData : this.getElementCarouselDatas()) {
            if (index == 0) {
                carouselDatasText += "<div class='carousel-item active'><img src='" + elementCarouselData.getTitle()
                        + "' class='d-block w-50' alt='...'></div>";
            }
            carouselDatasText += "<div class='carousel-item'><img src='" + elementCarouselData.getTitle()
                    + "' class='d-block w-50' alt='...'></div>";
            index++;
        }

        return "<div id='carousel" + this.getId().toString()
                + "' class='carousel slide' data-bs-ride='carousel'><div class='carousel-inner'>" + carouselDatasText
                + "</div><button class='carousel-control-prev' type='button' data-bs-target='#carousel"
                + this.getId().toString()
                + "' data-bs-slide='prev'><span class='carousel-control-prev-icon' aria-hidden='true'></span><span class='visually-hidden'>Previous</span></button><button class='carousel-control-next' type='button' data-bs-target='#carousel"
                + this.getId().toString()
                + "' data-bs-slide='next'><span class='carousel-control-next-icon' aria-hidden='true'></span><span class='visually-hidden'>Next</span></button></div>";
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
