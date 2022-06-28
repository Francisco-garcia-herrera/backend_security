package com.example.backend_security.domain.entities;

import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;

public interface ElementInterface {
    String render();

    ElementHttpRestEntity mapToDto(Element element);
}
