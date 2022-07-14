package com.example.backend_security.domain.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.database.entities.JpaPage;
import com.example.backend_security.infrastucture.database.entities.JpaUnit;
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

    public UnitHttpRestEntity mapToDto() {
        return UnitHttpRestEntity.builder()
                .id(this.getId())
                .name(this.getName())
                .pages(this.getPages() != null
                        ? this.getPages().stream().map(DomainToDtoAdapter::convert).collect(Collectors.toList())
                        : null)
                .build();
    }

    public UnitHttpRestEntity mapToDtoReduced() {
        return UnitHttpRestEntity.builder()
                .id(this.getId())
                .build();
    }

    public JpaUnit mapToJpa() {
        List<JpaPage> jpaPages = new ArrayList<>();
        if (this.getPages() != null) {
            for (Page page : this.getPages()) {
                JpaPage jpaPage = page.mapToJpa();
                jpaPages.add(jpaPage);
            }
        }
        JpaUnit jpaUnit = new JpaUnit(this.getId(), this.getName(), jpaPages);
        return jpaUnit;
    }

}
