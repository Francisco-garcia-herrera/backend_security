package com.example.backend_security.infrastucture.http.httprestentities;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementImage;

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
public class ElementImageHttpRestEntity extends ElementHttpRestEntity {

    private String type;

    private String url;

    public ElementImageHttpRestEntity(Integer position, String type, String url) {
        super(position);
        this.type = type;
        this.url = url;
    }

    public ElementImageHttpRestEntity(Long id, Integer position, String type, String url) {
        super(id, position);
        this.type = type;
        this.url = url;
    }

    public Element mapToDomain(ElementHttpRestEntity elementHttpRestEntity) {
        Element element = new ElementImage(elementHttpRestEntity.getId(), elementHttpRestEntity.getPosition(),
                this.type, this.url);
        return element;
    }

}
