package com.example.backend_security.infrastucture.http.httprestentities;

import com.example.backend_security.domain.entities.Element;

public interface ElementInterfaceHttpRestEntity {

    public Element mapToDomain(ElementHttpRestEntity elementHttpRestEntity);
}
