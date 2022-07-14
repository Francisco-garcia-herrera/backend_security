package com.example.backend_security.infrastucture.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementCarousel;
import com.example.backend_security.domain.entities.ElementCarouselData;
import com.example.backend_security.domain.entities.Page;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "element_carousel")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "element_id")
@EqualsAndHashCode(callSuper = false, exclude = { "elementCarouselDatas" })
public class JpaElementCarousel extends JpaElement {

    private String type;

    private String title;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "elementCarousel")
    @JsonIgnoreProperties("elementCarousel")
    private List<JpaElementCarouselData> elementCarouselDatas;

    public JpaElementCarousel(Integer position, JpaPage jpaPage, String type, String title,
            List<JpaElementCarouselData> elementCarouselDatas) {
        super(position, jpaPage);
        this.type = type;
        this.title = title;
        this.elementCarouselDatas = elementCarouselDatas;
    }

    public JpaElementCarousel(Long id, Integer position, JpaPage jpaPage, String type, String title,
            List<JpaElementCarouselData> elementCarouselDatas) {
        super(id, position, jpaPage);
        this.type = type;
        this.title = title;
        this.elementCarouselDatas = elementCarouselDatas;
    }

    public Element mapToDomain() {
        return null;
    }

    public Element mapToDomainReduced() {
        Page page = new Page();
        page.setId(this.getPage().getId());

        List<ElementCarouselData> elementCarouselDatas = new ArrayList<>();
        if (this.elementCarouselDatas != null) {
            for (JpaElementCarouselData jpaElementCarouselData : this.elementCarouselDatas) {

                ElementCarousel elementCarousel = new ElementCarousel();

                if (jpaElementCarouselData.getElementCarousel() != null) {
                    elementCarousel.setId(jpaElementCarouselData.getElementCarousel().getId());
                }

                ElementCarouselData elementCarouselData = new ElementCarouselData(jpaElementCarouselData.getId(),
                        jpaElementCarouselData.getType(), jpaElementCarouselData.getTitle(),
                        elementCarousel);
                elementCarouselDatas.add(elementCarouselData);
            }
        }

        Element element = new ElementCarousel(this.getId(), this.getPosition(), page, this.getType(), this.getTitle(),
                elementCarouselDatas);
        return element;
    }

    public ElementCarousel mapToElementCarrousel(JpaElementCarousel jpaElementCarousel) {
        ElementCarousel elementCarousel = new ElementCarousel();
        if (jpaElementCarousel.getId() != null) {
            elementCarousel.setId(jpaElementCarousel.getId());
        }
        return elementCarousel;
    }

}
