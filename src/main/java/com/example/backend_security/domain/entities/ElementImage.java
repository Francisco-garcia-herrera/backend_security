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

    public ElementImage(Long id, Integer position, String type, String url) {
        super(id, position);
        this.type = type;
        this.url = url;
    }

    public String render() {
        return "Elemento Imagen: " + this.getPosition() + ". Url: " + this.url;
    }

    public ElementHttpRestEntity mapToDto(Element element) {
        ElementHttpRestEntity elementHttpRestEntity = new ElementImageHttpRestEntity(element.getId(),
                this.getPosition(), this.type, this.url);
        return elementHttpRestEntity;
    }

    public JpaElement mapToJpa(Element element) {
        JpaElement jpaElement = new JpaElementImage(this.getId(), this.getPosition(), this.type, this.url);
        return jpaElement;
    }
}
