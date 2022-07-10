package com.example.backend_security.domain.usecases.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.Page;
import com.example.backend_security.domain.repositories.ElementRepository;
import com.example.backend_security.domain.repositories.PageRepository;

@Service
public class CreatePage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PageRepository pageRepository;

    public Page create(Page pageToCreate) {

        logger.info("Create Element Usecase");
        return pageRepository.save(pageToCreate);
    }

}
