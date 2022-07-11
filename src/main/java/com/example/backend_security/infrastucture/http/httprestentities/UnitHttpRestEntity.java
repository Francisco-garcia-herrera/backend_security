package com.example.backend_security.infrastucture.http.httprestentities;

import java.util.ArrayList;
import java.util.List;

import com.example.backend_security.domain.entities.Page;
import com.example.backend_security.domain.entities.Unit;

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
public class UnitHttpRestEntity {
    private Long id;

    private String name;

    private List<PageHttpRestEntity> pages;

    public Unit mapToDomain(UnitHttpRestEntity unitHttpRestEntity) {

        List<Page> pages = new ArrayList<>();
        if (unitHttpRestEntity.getPages() != null) {
            for (PageHttpRestEntity pagetHttpRestEntity : unitHttpRestEntity.getPages()) {
                pages.add(pagetHttpRestEntity.mapToDomain(pagetHttpRestEntity));
            }
        }
        Unit unit = new Unit(unitHttpRestEntity.getId(), unitHttpRestEntity.getName(), pages);

        return unit;
    }

}
