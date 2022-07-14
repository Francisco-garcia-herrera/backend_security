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

    @Override
    public String getType() {
        return this.type;
    }

    public ElementImageHttpRestEntity(Integer position, PageHttpRestEntity page, String type, String url) {
        super(position, page);
        this.type = type;
        this.url = url;
    }

    public ElementImageHttpRestEntity(Long id, Integer position, PageHttpRestEntity page, String type, String url) {
        super(id, position, page);
        this.type = type;
        this.url = url;
    }

    public Element mapToDomain() {
        Element element = new ElementImage(this.getId(), this.getPosition(),
                this.getPage().mapToDomain(this.getPage()),
                this.type, this.url);
        return element;
    }

}
