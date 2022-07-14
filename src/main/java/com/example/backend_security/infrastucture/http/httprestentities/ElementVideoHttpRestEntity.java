package com.example.backend_security.infrastucture.http.httprestentities;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementVideo;

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
public class ElementVideoHttpRestEntity extends ElementHttpRestEntity {

    private String type;

    private String codec;

    @Override
    public String getType() {
        return this.type;
    }

    public ElementVideoHttpRestEntity(Long id, Integer position, PageHttpRestEntity page, String type, String codec) {
        super(id, position, page);
        this.type = type;
        this.codec = codec;
    }

    public Element mapToDomain() {
        Element element = new ElementVideo(this.getId(), this.getPosition(),
                this.getPage().mapToDomain(),
                this.type, this.codec);
        return element;
    }

}
