package com.example.backend_security.domain.entities;

import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaElementImage;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;
import com.example.backend_security.infrastucture.http.httprestentities.ElementImageHttpRestEntity;

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
public class ElementImage extends Element {

    private String type;
    private String url;

    public ElementImage(Integer position, String type, String url) {
        super(position);
        this.type = type;
        this.url = url;
    }

    public ElementImage(Long id, Integer position, Page page, String type, String url) {
        super(id, position, page);
        this.type = type;
        this.url = url;
    }

    public String render() {
        return "Elemento Imagen: " + this.getPosition() + ". Url: " + this.url;
    }

    public ElementHttpRestEntity mapToDto() {
        ElementHttpRestEntity elementHttpRestEntity = new ElementImageHttpRestEntity(this.getId(),
                this.getPosition(), this.getPage().mapToDtoReduced(), this.type, this.url);
        return elementHttpRestEntity;
    }

    public JpaElement mapToJpa() {
        JpaElement jpaElement = new JpaElementImage(this.getId(), this.getPosition(),
                this.getPage().mapToJpa(), this.type, this.url);
        return jpaElement;
    }
}
