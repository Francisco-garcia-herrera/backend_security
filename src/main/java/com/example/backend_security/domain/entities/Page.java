package com.example.backend_security.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaPage;
import com.example.backend_security.infrastucture.database.entities.JpaPage.JpaPageType;
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

    private Unit unit;

    private String type;

    private Integer position;

    public PageHttpRestEntity mapToDto() {
        return PageHttpRestEntity.builder()
                .id(this.getId())
                .name(this.getName())
                .elements(this.getElements() != null
                        ? this.getElements().stream().map(DomainToDtoAdapter::convert).collect(Collectors.toList())
                        : null)
                .unit(this.getUnit().mapToDtoReduced())
                .type(this.type)
                .position(this.position)
                .build();
    }

    public PageHttpRestEntity mapToDtoReduced() {
        return PageHttpRestEntity.builder()
                .id(this.getId())
                .name(this.getName() != null ? this.getName() : null)
                .unit(this.getUnit() != null ? this.getUnit().mapToDtoReduced() : null)
                .type(this.type)
                .position(this.position)
                .build();
    }

    public JpaPage mapToJpa() {
        List<JpaElement> jpaElements = new ArrayList<>();
        if (this.getElements() != null) {
            for (Element element : this.getElements()) {
                JpaElement jpaElement = element.mapToJpa();
                jpaElements.add(jpaElement);
            }
        }
        JpaPage jpaPage = new JpaPage(this.getId(), this.getName() != null ? this.getName() : null,
                this.getType() != null ? JpaPageType.valueOf(this.getType()) : null, jpaElements, this.position);
        return jpaPage;
    }

}
