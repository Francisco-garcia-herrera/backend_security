package com.example.backend_security.infrastucture.adapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.infrastucture.database.entities.JpaElement;

@Component
public class DomainToJpaAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public JpaElement convert(Element object) {
        logger.info("Conver Element to JpaElement");
        return null;
    }
}
