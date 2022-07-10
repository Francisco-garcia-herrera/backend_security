package com.example.backend_security.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ElementCarouselData {
    private Long id;
    private String type;
    private String title;
    private ElementCarousel elementCarousel;
}
