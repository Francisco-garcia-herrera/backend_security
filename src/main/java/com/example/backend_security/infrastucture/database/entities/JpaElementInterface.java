package com.example.backend_security.infrastucture.database.entities;

import com.example.backend_security.domain.entities.Element;

public interface JpaElementInterface {
    public Element mapToDomain(JpaElement jpaElement);

}
