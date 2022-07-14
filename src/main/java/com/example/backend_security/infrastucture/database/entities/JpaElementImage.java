package com.example.backend_security.infrastucture.database.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementImage;
import com.example.backend_security.domain.entities.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "element_image")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "element_id")
public class JpaElementImage extends JpaElement {

    private String type;

    private String url;

    public JpaElementImage(Integer position, JpaPage jpaPage, String type, String url) {
        super(position, jpaPage);
        this.type = type;
        this.url = url;
    }

    public JpaElementImage(Long id, Integer position, JpaPage jpaPage, String type, String url) {
        super(id, position, jpaPage);
        this.type = type;
        this.url = url;
    }

    public Element mapToDomain() {
        Element element = new ElementImage(this.getId(), this.getPosition(),
                this.getPage().mapToDomain(), this.type, this.url);
        return element;
    }

    public Element mapToDomainReduced() {
        Page page = new Page();
        page.setId(this.getPage().getId());

        Element element = new ElementImage(this.getId(), this.getPosition(),
                page, this.type, this.url);
        return element;
    }

}
