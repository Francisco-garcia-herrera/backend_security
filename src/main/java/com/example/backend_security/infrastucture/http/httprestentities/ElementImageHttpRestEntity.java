package com.example.backend_security.infrastucture.http.httprestentities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
public class ElementImageHttpRestEntity extends ElementHttpRestEntity {
    private String url;

    public ElementImageHttpRestEntity(Long id, Integer position, String url) {
        super(id, position);
        this.url = url;
    }

}
