package com.example.backend_security.domain.entities;

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
public class ElementVideo extends Element {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String codec;

    public ElementVideo(Integer position, String codec) {
        super(position);
        this.codec = codec;
    }

    public ElementVideo(Long id, Integer position, String codec) {
        super(id, position);
        this.codec = codec;
    }

    public String render() {
        return "Elemento Video: " + this.getPosition() + ". Url: " + this.codec;
    }
}
