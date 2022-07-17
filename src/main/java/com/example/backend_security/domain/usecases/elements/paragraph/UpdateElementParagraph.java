package com.example.backend_security.domain.usecases.elements.paragraph;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.repositories.ElementRepository;

@Service
public class UpdateElementParagraph {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ElementRepository elementRepository;

    public Element update(Element elementToUpdate) {
        logger.info("Update Element PARAGRAPH Usecase");
        return elementRepository.update(elementToUpdate);
    }

}
