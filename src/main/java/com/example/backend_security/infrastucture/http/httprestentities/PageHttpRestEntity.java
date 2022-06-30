package com.example.backend_security.infrastucture.http.httprestentities;

import java.util.List;

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

}
