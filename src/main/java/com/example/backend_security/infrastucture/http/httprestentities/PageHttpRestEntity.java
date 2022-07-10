package com.example.backend_security.infrastucture.http.httprestentities;

import java.util.ArrayList;
import java.util.List;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageHttpRestEntity {
    private Long id;

    private String name;

    private List<ElementHttpRestEntity> elements;

    public Page mapToDomain(PageHttpRestEntity pageHttpRestEntity) {

        List<Element> elements = new ArrayList<>();
        if (pageHttpRestEntity.getElements() != null) {
            for (ElementHttpRestEntity elementHttpRestEntity : pageHttpRestEntity.getElements()) {
                Element element = elementHttpRestEntity.mapToDomain(elementHttpRestEntity);
                elements.add(element);
            }
        }
        Page page = new Page(pageHttpRestEntity.getId(), pageHttpRestEntity.getName(), elements);

        return page;
    }

}
