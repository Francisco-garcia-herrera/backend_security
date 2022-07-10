package com.example.backend_security.domain.usecases.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend_security.domain.entities.Page;
import com.example.backend_security.domain.repositories.PageRepository;

@Service
public class UpdatePage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PageRepository pageRepository;

    public Page update(Page pageToUpdate) {
        logger.info("Update Page Usecase");
        return pageRepository.update(pageToUpdate);
    }

}
