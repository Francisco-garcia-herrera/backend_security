package com.example.backend_security.infrastucture.database.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementImage;

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
    private String url;

    public JpaElementImage(Integer position, String url) {
        super(position);
        this.url = url;
    }

    public JpaElementImage(Long id, Integer position, String url) {
        super(id, position);
        this.url = url;
    }

    public Element mapToDomain(JpaElement jpaElement) {
        Element element = new ElementImage(jpaElement.getId(), this.getPosition(), this.url);
        return element;
    }

}
