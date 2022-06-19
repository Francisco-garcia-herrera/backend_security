package com.example.backend_security.domain.usecases.elements;

import java.util.List;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.repositories.ElementRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GetAllElements {

    @Autowired
    ElementRepository elementRepository;

    public List<Element> get() {
        List<Element> elements = elementRepository.getAll();
        return elements;
    }

}
