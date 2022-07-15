package com.example.backend_security.domain.usecases.unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend_security.domain.repositories.PageRepository;
import com.example.backend_security.domain.repositories.UnitRepository;

@Service
public class DeleteUnit {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UnitRepository unitRepository;

    public String delete(Long id) {
        logger.info("Delete Page Usecase");
        unitRepository.delete(id);
        return "Page Delete Successfully";
    }

}
