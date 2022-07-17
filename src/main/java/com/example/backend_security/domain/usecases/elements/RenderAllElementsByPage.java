package com.example.backend_security.domain.usecases.elements;

import java.util.ArrayList;
import java.util.List;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.repositories.ElementRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class RenderAllElementsByPage {

    @Autowired
    ElementRepository elementRepository;

    public List<String> get(Long pageId) {
        List<Element> elements = elementRepository.renderAllByPage(pageId);
        List<String> elementRenders = new ArrayList<>();
        if (elements != null) {
            for (Element element : elements) {
                elementRenders.add(element.render());
            }
        }
        return elementRenders;
    }

}
