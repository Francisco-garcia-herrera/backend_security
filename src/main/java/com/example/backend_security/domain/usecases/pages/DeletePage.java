package com.example.backend_security.domain.usecases.pages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend_security.domain.repositories.PageRepository;

@Service
public class DeletePage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PageRepository pageRepository;

    public String delete(Long id) {
        logger.info("Delete Page Usecase");
        pageRepository.delete(id);
        return "Page Delete Successfully";
    }

}
