package com.example.backend_security.domain.entities;

import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaElementParagraph;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;
import com.example.backend_security.infrastucture.http.httprestentities.ElementParagraphHttpRestEntity;

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
public class ElementParagraph extends Element {

    private String type;
    private String content;

    public ElementParagraph(Integer position, Page page, String type, String content) {
        super(position, page);
        this.type = type;
        this.content = content;
    }

    public ElementParagraph(Long id, Integer position, Page page, String type, String content) {
        super(id, position, page);
        this.type = type;
        this.content = content;
    }

    public String render() {
        return "<div class='d-flex justify-content-start'><p>" + this.content + "</p></div>";
    }

    public ElementHttpRestEntity mapToDto() {
        ElementHttpRestEntity elementHttpRestEntity = new ElementParagraphHttpRestEntity(this.getId(),
                this.getPosition(), this.getPage().mapToDtoReduced(), this.type, this.content);
        return elementHttpRestEntity;
    }

    public JpaElement mapToJpa() {
        JpaElement jpaElement = new JpaElementParagraph(this.getId(), this.getPosition(),
                this.getPage().mapToJpa(), JpaElement.JpaElementType.valueOf(this.type), this.content);
        return jpaElement;
    }
}
