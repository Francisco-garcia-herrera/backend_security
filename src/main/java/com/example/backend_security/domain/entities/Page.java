package com.example.backend_security.domain.entities;

import java.util.List;
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
                .build();
    }

}
