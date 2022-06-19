package com.example.backend_security.domain.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaElementVideo;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String codec;

    public ElementVideo(Integer position, String codec) {
        super(position);
        this.codec = codec;
    }

    public ElementVideo(Long id, Integer position, String codec) {
        super(id, position);
        this.codec = codec;
    }

    public ElementHttpRestEntity fromEntity(Element element) {
        ElementHttpRestEntity elementHttpRestEntity = new ElementVideoHttpRestEntity(element.getId(),
                this.getPosition(), this.codec);
        logger.info("Convert ElementVideo to ElementHttpRest");
        return elementHttpRestEntity;
    }

    public Element toEntity(ElementHttpRestEntity elementHttpRestEntity) {
        Element element = new ElementVideo(elementHttpRestEntity.getId(),
                this.getPosition(), this.codec);
        logger.info("Convert ElementHttpRestEntity to Element Domain");
        return element;
    }

    public String render() {
        return "Elemento Video: " + this.getPosition() + ". Url: " + this.codec;
    }
}
