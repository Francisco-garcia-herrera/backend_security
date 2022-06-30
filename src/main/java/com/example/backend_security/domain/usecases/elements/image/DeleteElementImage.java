package com.example.backend_security.domain.usecases.elements.image;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend_security.domain.repositories.ElementRepository;

@Service
public class DeleteElementImage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ElementRepository elementRepository;

    public String delete(Long id) {
        logger.info("Delete Element Usecase");
        elementRepository.delete(id);
        return "Element Delete Successfully";
    }

}
