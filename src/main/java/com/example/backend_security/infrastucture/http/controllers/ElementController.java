package com.example.backend_security.infrastucture.http.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.usecases.elements.CreateElement;
import com.example.backend_security.domain.usecases.elements.DeleteElement;
import com.example.backend_security.domain.usecases.elements.GetAllElements;
import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;
import com.example.backend_security.infrastucture.http.httprestentities.ElementImageHttpRestEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Lazy
@RestController
@RequestMapping(path = "/elements", consumes = { MediaType.APPLICATION_JSON_VALUE,
        MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
public class ElementController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GetAllElements getAllElements;
    @Autowired
    private DomainToDtoAdapter domainToDtoAdapter;
    @Autowired
    private CreateElement createElement;
    @Autowired
    private DeleteElement deleteElement;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        List<ElementHttpRestEntity> body = new ArrayList<>();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        List<Element> elementsDomain = new ArrayList<>();

        elementsDomain = getAllElements.get();
        body = domainToDtoAdapter.convert(elementsDomain);

        toReturn = new ResponseEntity<>(body, status);
        return toReturn;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ElementImageHttpRestEntity data) {
        ElementHttpRestEntity body = null;
        Element createdElement = null;
        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<?> toReturn;
        try {
            // Long userId = getAuthUserId();
            Element element = data.mapToDomain(data);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@RequestBody ElementImageHttpRestEntity elementImageHttpRestEntity) {
        String body = "";
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        try {
            Element element = elementImageHttpRestEntity.mapToDomain(elementImageHttpRestEntity);
            body = deleteElement.delete(element);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Generic uncontrolled ERROR", e);
            return new ResponseEntity<>(e.getMessage(), status);
        } finally {
            toReturn = new ResponseEntity<>(elementImageHttpRestEntity, status);
            logger.debug(". Status<" + status + ">");
        }
        return toReturn;
    }

}
