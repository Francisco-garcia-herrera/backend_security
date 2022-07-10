package com.example.backend_security.infrastucture.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.Page;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pages")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(exclude = "elements")
public class JpaPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "page")
    @JsonIgnoreProperties("page")
    private List<JpaElement> elements;

    public Page mapToDomain(JpaPage jpaPage) {
        List<Element> elements = new ArrayList<>();
        for (JpaElement jpaElement : jpaPage.getElements()) {
            Element element = jpaElement.mapToDomainReduced(jpaElement);
            elements.add(element);
        }
        Page page = new Page(jpaPage.getId(), jpaPage.getName(), elements);
        return page;
    }

}
