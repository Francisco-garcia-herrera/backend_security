package com.example.backend_security.domain.usecases.elements.carouseldata;

import com.example.backend_security.domain.entities.ElementCarouselData;
import com.example.backend_security.domain.repositories.ElementCarouselDataRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GetElementCarouselData {

    @Autowired
    ElementCarouselDataRepository elementCarouselDataRepository;

    public ElementCarouselData get(Long elementCarouselDataId) {
        ElementCarouselData elementCarouselData = elementCarouselDataRepository.getById(elementCarouselDataId);
        return elementCarouselData;
    }

}
