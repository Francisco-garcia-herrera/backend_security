package com.example.backend_security.infrastucture.adapter;

import org.springframework.stereotype.Component;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;

@Component
public class TypeToDomainAdapter {

    public Element convert(ElementHttpRestEntity object) {

        return null;
    }

}
