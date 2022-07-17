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

    public ElementVideo(Integer position, Page page, String type, String codec) {
        super(position, page);
        this.type = type;
        this.codec = codec;
    }

    public ElementVideo(Long id, Integer position, Page page, String type, String codec) {
        super(id, position, page);
        this.type = type;
        this.codec = codec;
    }

    public String render() {
        return "<div>" + this.codec + "</div>";
    }

    public ElementHttpRestEntity mapToDto() {
        ElementHttpRestEntity elementHttpRestEntity = new ElementVideoHttpRestEntity(this.getId(),
                this.getPosition(), this.getPage().mapToDtoReduced(), this.type, this.codec);
        return elementHttpRestEntity;
    }

    public JpaElement mapToJpa() {
        JpaElement jpaElement = new JpaElementImage(this.getId(), this.getPosition(),
                this.getPage().mapToJpa(), JpaElement.JpaElementType.valueOf(this.type), this.codec);
        return jpaElement;
    }
}
