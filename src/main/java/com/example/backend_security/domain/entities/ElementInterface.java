package com.example.backend_security.domain.entities;

import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;

public interface ElementInterface {

    public ElementHttpRestEntity fromEntity(Element element);

    public Element toEntity(ElementHttpRestEntity elementHttpRestEntity);

    public String render();
}
