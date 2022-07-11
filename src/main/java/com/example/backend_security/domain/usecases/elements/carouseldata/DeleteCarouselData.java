package com.example.backend_security.domain.usecases.elements.carouseldata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend_security.domain.repositories.ElementCarouselDataRepository;

@Service
public class DeleteCarouselData {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ElementCarouselDataRepository elementCarouselDataRepository;

    public String delete(Long id) {
        logger.info("Delete Element Carousel Data Usecase");
        elementCarouselDataRepository.delete(id);
        return "Carousel Data Delete Successfully";
    }

}
