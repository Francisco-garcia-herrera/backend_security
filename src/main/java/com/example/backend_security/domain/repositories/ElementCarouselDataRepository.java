package com.example.backend_security.domain.repositories;

import com.example.backend_security.domain.entities.ElementCarouselData;

public interface ElementCarouselDataRepository {

    ElementCarouselData getById(Long elementId);

    ElementCarouselData save(ElementCarouselData elementCarouselData);

}
