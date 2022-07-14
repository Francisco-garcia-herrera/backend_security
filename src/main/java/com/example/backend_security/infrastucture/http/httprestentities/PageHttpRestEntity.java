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

    public Page mapToDomain() {

        List<Element> elements = new ArrayList<>();
        if (this.getElements() != null) {
            for (ElementHttpRestEntity elementHttpRestEntity : this.getElements()) {
                Element element = elementHttpRestEntity.mapToDomain();
                elements.add(element);
            }
        }
        Page page = new Page(this.getId(), this.getName(), elements);

        return page;
    }

}
