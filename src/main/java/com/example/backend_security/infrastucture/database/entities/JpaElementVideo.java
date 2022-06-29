package com.example.backend_security.infrastucture.database.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementVideo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "element_video")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name = "element_id")
public class JpaElementVideo extends JpaElement {

    private String type;

    private String codec;

    public JpaElementVideo(Integer position, String type, String codec) {
        super(position);
        this.type = type;
        this.codec = codec;

    }

    public JpaElementVideo(Long id, Integer position, String type, String codec) {
        super(id, position);
        this.type = type;
        this.codec = codec;
    }

    public Element mapToDomain(JpaElement jpaElement) {
        Element element = new ElementVideo(jpaElement.getId(), jpaElement.getPosition(), this.type, this.codec);
        return element;
    }

    public JpaElement mapToJpa(Element element) {
        JpaElement jpaElement = new JpaElementVideo(this.getId(), this.getPosition(), this.type, this.codec);
        return jpaElement;
    }
}
