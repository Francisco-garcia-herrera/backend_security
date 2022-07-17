package com.example.backend_security.infrastucture.http.httprestentities;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementImage;
import com.example.backend_security.domain.entities.ElementParagraph;

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
public class ElementParagraphHttpRestEntity extends ElementHttpRestEntity {

    private String type;

    private String content;

    @Override
    public String getType() {
        return this.type;
    }

    public ElementParagraphHttpRestEntity(Integer position, PageHttpRestEntity page, String type, String content) {
        super(position, page);
        this.type = type;
        this.content = content;
    }

    public ElementParagraphHttpRestEntity(Long id, Integer position, PageHttpRestEntity page, String type,
            String content) {
        super(id, position, page);
        this.type = type;
        this.content = content;
    }

    public Element mapToDomain() {
        Element element = new ElementParagraph(this.getId(), this.getPosition(),
                this.getPage().mapToDomain(),
                this.type, this.content);
        return element;
    }

}
