package com.example.backend_security.domain.entities;

import java.util.List;
import java.util.stream.Collectors;

import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.http.httprestentities.UnitHttpRestEntity;

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
public class Unit {

    private Long id;

    private String name;

    private List<Page> pages;

    public UnitHttpRestEntity mapToDto(Unit unit) {
        return UnitHttpRestEntity.builder()
                .id(this.getId())
                .name(this.getName())
                .pages(unit.getPages() != null
                        ? unit.getPages().stream().map(DomainToDtoAdapter::convert).collect(Collectors.toList())
                        : null)
                .build();
    }

}
