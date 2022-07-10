package com.example.backend_security.domain.usecases.elements.carousel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementCarouselData;
import com.example.backend_security.domain.repositories.ElementCarouselDataRepository;
import com.example.backend_security.domain.repositories.ElementRepository;

@Service
public class CreateElementCarouselData {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ElementCarouselDataRepository elementCarouselDataRepository;

    public ElementCarouselData create(ElementCarouselData elementToCreate) {
        logger.info("Create Element CAROUSEL DATA Usecase");
        return elementCarouselDataRepository.save(elementToCreate);
    }

}
