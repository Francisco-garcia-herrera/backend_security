package com.example.backend_security.domain.usecases.elements;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.repositories.ElementRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GetElement {

    @Autowired
    ElementRepository elementRepository;

    public Element get(Long pageId) {
        Element element = elementRepository.getElementById(pageId);
        return element;
    }

}
