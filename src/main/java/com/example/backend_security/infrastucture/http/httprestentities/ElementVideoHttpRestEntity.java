package com.example.backend_security.infrastucture.http.httprestentities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementVideo;

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
public class ElementVideoHttpRestEntity extends ElementHttpRestEntity {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String codec;
    private Object url;

    public ElementVideoHttpRestEntity(Long id, Integer position, String codec) {
        super(id, position);
        this.codec = codec;
    }

}
