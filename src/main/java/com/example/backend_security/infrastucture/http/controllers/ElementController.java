package com.example.backend_security.infrastucture.http.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.usecases.elements.GetAllElements;
import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaElementImage;
import com.example.backend_security.infrastucture.database.entities.JpaElementVideo;
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
    JpaElementQueries jpaElementQueries;

    /*
     * @Autowired
     * private CreateElement createElement;
     */

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        List<ElementHttpRestEntity> body = new ArrayList<>();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        List<Element> elements = new ArrayList<>();
        List<Element> element2 = new ArrayList<>();
        try {
            /* Long userId = this.getAuthUserId(); */

            elements = getAllElements.get();
            System.out.println(elements.toString());

            /*
             * if (elements != null) {
             * for (Element element : elements) {
             * body.add(element.fromEntity(element));
             * }
             * }
             */

        } catch (Exception e) {
            status = HttpStatus.FORBIDDEN;
            logger.error("Generic uncontrolled ERROR GET ALL", e);
            // return new ResponseEntity<>(e.getMessage(), status);
        } finally {
            toReturn = new ResponseEntity<>(elements, status);
            logger.debug(". Status<" + status + ">");
        }
        return toReturn;
    }

    @GetMapping("/create")
    public String createMock(@RequestParam(value = "name", defaultValue = "World") String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getAuthorities());
        System.out.println(auth.isAuthenticated());

        List<JpaElement> elements = new ArrayList<>();
        elements.add(new JpaElementImage(1, "URL IMAGE"));
        elements.add(new JpaElementVideo(2, "H264"));
        elements.add(new JpaElementImage(3, "URL IMAGE 2"));
        elements.add(new JpaElementVideo(4, "H264 2"));
        for (JpaElement jpaElement : elements) {
            jpaElementQueries.saveAndFlush(jpaElement);
        }

        return "Elements Created";
    }

}
