package com.example.backend_security.domain.entities;

import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;

public interface ElementInterface {
    String render();

    ElementHttpRestEntity mapToDto();

    JpaElement mapToJpa();
}
