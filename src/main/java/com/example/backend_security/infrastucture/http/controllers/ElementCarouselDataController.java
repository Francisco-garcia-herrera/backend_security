package com.example.backend_security.infrastucture.http.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_security.domain.entities.ElementCarouselData;
import com.example.backend_security.domain.usecases.elements.carousel.CreateElementCarouselData;
import com.example.backend_security.domain.usecases.elements.carouseldata.GetElementCarouselData;
import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.http.httprestentities.ElementCarouselDataHttpRestEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Lazy
@RestController
@RequestMapping(path = "/elements/carousel-data", consumes = { MediaType.APPLICATION_JSON_VALUE,
        MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
public class ElementCarouselDataController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DomainToDtoAdapter domainToDtoAdapter;
    @Autowired
    private GetElementCarouselData getElementCarouselData;
    @Autowired
    private CreateElementCarouselData createElementCarouselData;

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ElementCarouselDataHttpRestEntity body = new ElementCarouselDataHttpRestEntity();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        ElementCarouselData elementCarouselDataDomain = null;

        elementCarouselDataDomain = getElementCarouselData.get(id);
        body = domainToDtoAdapter.convert(elementCarouselDataDomain);

        toReturn = new ResponseEntity<>(body, status);
        return toReturn;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody ElementCarouselDataHttpRestEntity data) {
        ElementCarouselDataHttpRestEntity body = null;
        ElementCarouselData createdElement = null;
        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<?> toReturn;
        try {
            // Long userId = getAuthUserId();
            ElementCarouselData elementCarouselData = data.mapToDomain(data);
            createdElement = createElementCarouselData.create(elementCarouselData);
            body = domainToDtoAdapter.convert(createdElement);

        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Generic uncontrolled ERROR", e);
            return new ResponseEntity<>(e.getMessage(), status);
        } finally {
            toReturn = new ResponseEntity<>(body, status);
            logger.debug(". Status<" + status + ">");
        }
        return toReturn;
    }

    /*
     * @PutMapping()
     * public ResponseEntity<?> update(@RequestBody ElementCarouselHttpRestEntity
     * data) {
     * ElementHttpRestEntity body = null;
     * Element savedElement = null;
     * HttpStatus status = HttpStatus.CREATED;
     * ResponseEntity<?> toReturn = null;
     * try {
     * Element element = data.mapToDomain(data);
     * savedElement = updateElementCarousel.update(element);
     * body = domainToDtoAdapter.convert(savedElement);
     * } catch (Exception e) {
     * status = HttpStatus.INTERNAL_SERVER_ERROR;
     * logger.error("Generic uncontrolled ERROR", e);
     * return new ResponseEntity<>(e.getMessage(), status);
     * } finally {
     * toReturn = new ResponseEntity<>(body, status);
     * logger.debug(". Status<" + status + ">");
     * }
     * return toReturn;
     * }
     */

}
