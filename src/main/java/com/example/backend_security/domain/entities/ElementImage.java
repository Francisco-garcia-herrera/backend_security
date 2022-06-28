package com.example.backend_security.domain.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private String url;

    public ElementImage(Integer position, String url) {
        super(position);
        this.url = url;
    }

    public ElementImage(Long id, Integer position, String url) {
        super(id, position);
        this.url = url;
    }

    public String render() {
        return "Elemento Imagen: " + this.getPosition() + ". Url: " + this.url;
    }

    public ElementHttpRestEntity mapToDto(Element element) {
        ElementHttpRestEntity elementHttpRestEntity = new ElementImageHttpRestEntity(element.getId(),
                this.getPosition(), this.url);
        return elementHttpRestEntity;
    }
}
