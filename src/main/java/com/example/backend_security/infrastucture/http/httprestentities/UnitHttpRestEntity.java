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

    public Unit mapToDomain() {

        List<Page> pages = new ArrayList<>();
        if (this.getPages() != null) {
            for (PageHttpRestEntity pagetHttpRestEntity : this.getPages()) {
                pages.add(pagetHttpRestEntity.mapToDomain());
            }
        }
        Unit unit = new Unit(this.getId(), this.getName(), pages);

        return unit;
    }

}
