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
import com.example.backend_security.domain.usecases.elements.paragraph.CreateElementParagraph;
import com.example.backend_security.domain.usecases.elements.paragraph.UpdateElementParagraph;
import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;
import com.example.backend_security.infrastucture.http.httprestentities.ElementParagraphHttpRestEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Lazy
@RestController
@RequestMapping(path = "/elements/paragraph", consumes = { MediaType.APPLICATION_JSON_VALUE,
        MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
public class ElementParagraphController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CreateElementParagraph createParagraph;
    @Autowired
    UpdateElementParagraph updateElementParagraph;

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody ElementParagraphHttpRestEntity data) {
        ElementHttpRestEntity body = null;
        Element createdElement = null;
        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<?> toReturn;
        try {
            // Long userId = getAuthUserId();
            Element element = data.mapToDomain();
            createdElement = createParagraph.create(element);
            body = DomainToDtoAdapter.convert(createdElement);

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
    public ResponseEntity<?> update(@RequestBody ElementParagraphHttpRestEntity data) {
        ElementHttpRestEntity body = null;
        Element savedElement = null;
        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<?> toReturn = null;
        try {
            Element element = data.mapToDomain();
            savedElement = updateElementParagraph.update(element);
            body = DomainToDtoAdapter.convert(savedElement);
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
