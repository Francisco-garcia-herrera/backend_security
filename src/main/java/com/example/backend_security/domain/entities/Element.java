package com.example.backend_security.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Element implements ElementInterface {

    private Long id;

    private Integer position;

    private Page page;

    public Element(Integer position) {
        this.position = position;
    }

}
