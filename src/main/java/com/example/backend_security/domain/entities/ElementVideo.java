package com.example.backend_security.domain.entities;

import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaElementImage;
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

    private String type;

    private String codec;

    public ElementVideo(Integer position, String type, String codec) {
        super(position);
        this.type = type;
        this.codec = codec;
    }

    public ElementVideo(Long id, Integer position, Page page, String type, String codec) {
        super(id, position, page);
        this.type = type;
        this.codec = codec;
    }

    public String render() {
        return "Elemento Video: " + this.getPosition() + ". Codec: " + this.codec;
    }

    public ElementHttpRestEntity mapToDto(Element element) {
        ElementHttpRestEntity elementHttpRestEntity = new ElementVideoHttpRestEntity(element.getId(),
                this.getPosition(), this.getPage().mapToDto(this.getPage()), this.type, this.codec);
        return elementHttpRestEntity;
    }

    public JpaElement mapToJpa(Element element) {
        JpaElement jpaElement = new JpaElementImage(this.getId(), this.getPosition(), this.type, this.codec);
        return jpaElement;
    }
}
