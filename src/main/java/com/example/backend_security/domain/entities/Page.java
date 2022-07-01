package com.example.backend_security.domain.entities;

import java.util.List;
import java.util.stream.Collectors;

import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;
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
                .elements(page.getElements() != null ? page.getElements().stream().map(DomainToDtoAdapter::convert).collect(Collectors.toList()) : null)
                .build();
    }

}
