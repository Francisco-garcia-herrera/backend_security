package com.example.backend_security.infrastucture.database.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.backend_security.domain.entities.ElementCarouselData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "element_carousel_data")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "element_carousel_id")
public class JpaElementCarouselData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String title;

    /* @ManyToOne(cascade = { CascadeType.ALL }) */
    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "element_id")
    private JpaElementCarousel elementCarousel;

    public ElementCarouselData mapToDomain(JpaElementCarouselData jpaElementCarouselData) {
        ElementCarouselData elementCarouselData = new ElementCarouselData(jpaElementCarouselData.getId(),
                jpaElementCarouselData.getType(), jpaElementCarouselData.getTitle(), jpaElementCarouselData
                        .getElementCarousel().mapToElementCarrousel(jpaElementCarouselData.getElementCarousel()));
        return elementCarouselData;
    }

}
