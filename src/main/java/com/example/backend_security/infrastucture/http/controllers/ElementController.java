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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.usecases.elements.GetAllElements;
import com.example.backend_security.domain.usecases.elements.image.DeleteElementImage;
import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.queries.JpaElementQueries;
import com.example.backend_security.infrastucture.http.httprestentities.ElementHttpRestEntity;

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
    private DeleteElementImage deleteElement;
    @Autowired
    private JpaElementQueries jpaElementQueries;

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

    @GetMapping("/jpa")
    public ResponseEntity<?> jpa() {
        List<ElementHttpRestEntity> body = new ArrayList<>();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        List<JpaElement> elementsJpa = new ArrayList<>();

        elementsJpa = jpaElementQueries.findAll();
        /* body = domainToDtoAdapter.convert(elementsDomain); */

        toReturn = new ResponseEntity<>(elementsJpa, status);
        return toReturn;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        try {
            deleteElement.delete(id);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Generic uncontrolled ERROR", e);
            return new ResponseEntity<>(e.getMessage(), status);
        } finally {
            toReturn = new ResponseEntity<>("Succesfully deleted", status);
            logger.debug(". Status<" + status + ">");
        }
        return toReturn;
    }

}
