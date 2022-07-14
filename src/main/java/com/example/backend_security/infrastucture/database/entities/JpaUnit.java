package com.example.backend_security.infrastucture.database.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.backend_security.domain.entities.Page;
import com.example.backend_security.domain.entities.Unit;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "units")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "pages")
public class JpaUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.PERSIST, mappedBy = "unit")
    @JsonIgnoreProperties("unit")
    private List<JpaPage> pages;

    public Unit mapToDomain() {
        List<Page> pages = new ArrayList<>();
        if (this.getPages() != null) {
            for (JpaPage jpaPage : this.getPages()) {
                pages.add(jpaPage.mapToDomain());
            }
        }

        Unit unit = new Unit(this.getId(), this.getName(), pages);
        return unit;
    }
}
