package com.example.backend_security.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaPage;
import com.example.backend_security.infrastucture.http.httprestentities.PageHttpRestEntity;

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
public class Page {

    private Long id;

    private String name;

    private List<Element> elements;

    public PageHttpRestEntity mapToDto(Page page) {
        return PageHttpRestEntity.builder()
                .id(this.getId())
                .name(this.getName())
                .elements(page.getElements() != null
                        ? page.getElements().stream().map(DomainToDtoAdapter::convert).collect(Collectors.toList())
                        : null)
                .build();
    }

    public PageHttpRestEntity mapToDtoReduced(Page page) {
        return PageHttpRestEntity.builder()
                .id(this.getId())
                .name(this.getName())
                .build();
    }

    public JpaPage mapToJpa(Page page) {
        List<JpaElement> jpaElements = new ArrayList<>();
        if (page.getElements() != null) {
            for (Element element : page.getElements()) {
                JpaElement jpaElement = element.mapToJpa(element);
                jpaElements.add(jpaElement);
            }
        }
        JpaPage jpaPage = new JpaPage(page.getId(), page.getName(), jpaElements);
        return jpaPage;
    }

}
