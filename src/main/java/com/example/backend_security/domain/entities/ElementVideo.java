package com.example.backend_security.domain.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;
import com.example.backend_security.infrastucture.http.httprestentities.ElementVideoHttpRestEntity;

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
public class ElementVideo extends Element {
    private String codec;

    public ElementVideo(Integer position, String codec) {
        super(position);
        this.codec = codec;
    }

    public ElementVideo(Long id, Integer position, String codec) {
        super(id, position);
        this.codec = codec;
    }

    public String render() {
        return "Elemento Video: " + this.getPosition() + ". Url: " + this.codec;
    }

    public ElementHttpRestEntity mapToDto(Element element) {
        ElementHttpRestEntity elementHttpRestEntity = new ElementVideoHttpRestEntity(element.getId(),
                this.getPosition(), this.codec);
        return elementHttpRestEntity;
    }
}
