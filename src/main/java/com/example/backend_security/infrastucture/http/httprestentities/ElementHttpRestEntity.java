package com.example.backend_security.infrastucture.http.httprestentities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ElementHttpRestEntity {
    private Long id;
    private Integer position;
}
