package com.example.backend_security.infrastucture.database.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.backend_security.domain.entities.Element;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "element_carousel")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "element_id")
public class JpaElementCarousel extends JpaElement {

    private String type;

    public JpaElementCarousel(Integer position, JpaPage jpaPage, String type) {
        super(position, jpaPage);
        this.type = type;
    }

    public JpaElementCarousel(Long id, Integer position, String type) {
        super(id, position);
        this.type = type;
    }

    public Element mapToDomain(JpaElement jpaElement) {
        return null;
    }

    public Element mapToDomainReduced(JpaElement jpaElement) {
        return null;
    }

}
