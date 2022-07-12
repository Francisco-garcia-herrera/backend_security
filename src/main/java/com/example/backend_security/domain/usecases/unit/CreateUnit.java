package com.example.backend_security.domain.usecases.unit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.backend_security.domain.entities.Unit;
import com.example.backend_security.domain.repositories.UnitRepository;

@Service
public class CreateUnit {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UnitRepository unitRepository;

    public Unit create(Unit unitToCreate) {

        logger.info("Create Unit Usecase");
        return unitRepository.save(unitToCreate);
    }

}
