package com.example.backend_security.infrastucture.adapter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementCarouselData;
import com.example.backend_security.domain.entities.Page;
import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaElementCarouselData;
import com.example.backend_security.infrastucture.database.entities.JpaPage;

@Component
public class DomainToJpaAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JpaElement convert(Element object) {
        logger.info("Conver Element to JpaElement");
        return object.mapToJpa(object);
    }

    public JpaPage convert(Page object) {
        List<JpaElement> jpaElements = new ArrayList<>();
        if (object.getElements() != null) {
            for (Element element : object.getElements()) {
                JpaElement jpaElement = element.mapToJpa(element);
                jpaElements.add(jpaElement);
            }
        }

        JpaPage jpaPage = new JpaPage(object.getId(), object.getName(), jpaElements);
        return jpaPage;
    }

    public JpaElementCarouselData convert(ElementCarouselData object) {
        JpaElementCarouselData jpaElementCarouselData = new JpaElementCarouselData(object.getId(), object.getType(),
                object.getTitle(), object.getElementCarousel().mapToJpaElementCarrousel(object.getElementCarousel()));
        return jpaElementCarouselData;
    }
}
