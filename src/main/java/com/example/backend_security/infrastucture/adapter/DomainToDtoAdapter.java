package com.example.backend_security.infrastucture.adapter;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.Page;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;
import com.example.backend_security.infrastucture.http.httprestentities.PageHttpRestEntity;

@Component
public class DomainToDtoAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ElementHttpRestEntity convert(Element object) {
        logger.info("Conver Element to Element HttpRest");
        return object.mapToDto(object);
    }

    public List<ElementHttpRestEntity> convert(List<Element> objects) {
        if (objects == null)
            return null;
        return objects.stream().map(this::convert).collect(Collectors.toList());
    }

    public List<PageHttpRestEntity> convertPage(List<Page> objects) {
        if (objects == null)
            return null;
        return objects.stream().map(this::convertPage).collect(Collectors.toList());
    }

    public PageHttpRestEntity convertPage(Page object) {
        if (object == null)
            return null;
        return object.mapToDto(object);
    }
}
