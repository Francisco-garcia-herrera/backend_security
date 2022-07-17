package com.example.backend_security.infrastucture.database.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementImage;
import com.example.backend_security.domain.entities.ElementParagraph;
import com.example.backend_security.domain.entities.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "element_paragraph")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "element_id")
public class JpaElementParagraph extends JpaElement {

    @Enumerated(EnumType.STRING)
    private JpaElementType type;

    private String content;

    public JpaElementParagraph(Integer position, JpaPage jpaPage, JpaElementType type, String content) {
        super(position, jpaPage);
        this.type = type;
        this.content = content;
    }

    public JpaElementParagraph(Long id, Integer position, JpaPage jpaPage, JpaElementType type, String content) {
        super(id, position, jpaPage);
        this.type = type;
        this.content = content;
    }

    public Element mapToDomain() {
        Element element = new ElementParagraph(this.getId(), this.getPosition(),
                this.getPage().mapToDomain(), this.type.toString(), this.content);
        return element;
    }

    public Element mapToDomainReduced() {
        Page page = new Page();
        page.setId(this.getPage().getId());

        Element element = new ElementParagraph(this.getId(), this.getPosition(),
                page, this.type.toString(), this.content);
        return element;
    }

}
