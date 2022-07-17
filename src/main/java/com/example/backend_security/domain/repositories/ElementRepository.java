package com.example.backend_security.domain.repositories;

import java.util.List;

import com.example.backend_security.domain.entities.Element;

public interface ElementRepository {

    List<Element> getAll();

    List<Element> renderAllByPage(Long pageId);

    Element getElementById(Long elementId);

    Element save(Element element);

    void delete(Long id);

    Element update(Element element);

}
