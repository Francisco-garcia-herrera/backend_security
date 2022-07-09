package com.example.backend_security.infrastucture.http.httprestentities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ElementHttpRestEntity implements ElementInterfaceHttpRestEntity {

    private Long id;

    private Integer position;

    private PageHttpRestEntity page;

    public ElementHttpRestEntity(Integer position, PageHttpRestEntity page) {
        this.position = position;
        this.page = page;
    }

    public String getType() {
        return "";
    }
}
