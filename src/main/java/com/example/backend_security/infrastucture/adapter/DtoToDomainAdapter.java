package com.example.backend_security.infrastucture.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;

@Component
public class DtoToDomainAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public Element convert(ElementHttpRestEntity object) {
        logger.info("Conver Element HttpRest to Element Domain");
        return object.mapToDomain();
    }

    public List<Element> convert(List<ElementHttpRestEntity> objects) {
        if (objects == null)
            return null;
        return objects.stream().map(this::convert).collect(Collectors.toList());
    }
}
