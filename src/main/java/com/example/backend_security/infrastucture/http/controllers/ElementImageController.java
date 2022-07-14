package com.example.backend_security.infrastucture.http.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.usecases.elements.image.CreateElementImage;
import com.example.backend_security.domain.usecases.elements.image.UpdateElementImage;
import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;
import com.example.backend_security.infrastucture.http.httprestentities.ElementImageHttpRestEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Lazy
@RestController
@RequestMapping(path = "/elements/image", consumes = { MediaType.APPLICATION_JSON_VALUE,
        MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
public class ElementImageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DomainToDtoAdapter domainToDtoAdapter;
    @Autowired
    private CreateElementImage createElement;
    @Autowired
    private UpdateElementImage updateElementImage;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody ElementImageHttpRestEntity data) {
        ElementHttpRestEntity body = null;
        Element createdElement = null;
        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<?> toReturn;
        try {
            // Long userId = getAuthUserId();
            Element element = data.mapToDomain();
            createdElement = createElement.create(element);
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

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody ElementImageHttpRestEntity data) {
        ElementHttpRestEntity body = null;
        Element savedElement = null;
        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<?> toReturn = null;
        try {
            Element element = data.mapToDomain();
            savedElement = updateElementImage.update(element);
            body = domainToDtoAdapter.convert(savedElement);
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

}
