package com.example.backend_security.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementImage;
import com.example.backend_security.domain.entities.ElementVideo;
import com.example.backend_security.infrastucture.adapter.JpaToDomainAdapter;
import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaElementImage;
import com.example.backend_security.infrastucture.database.entities.JpaElementVideo;
import com.example.backend_security.infrastucture.database.entities.JpaPage;
import com.example.backend_security.infrastucture.database.queries.JpaElementQueries;

@Lazy
@RestController
public class HelloController {

    @Autowired
    JpaElementQueries jpaElementQueries;

    @Autowired
    JpaToDomainAdapter jpaToDomainAdapter;

    @GetMapping("/hello")
    public List<String> greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(auth.getPrincipal());
        System.out.println(auth.getAuthorities());
        System.out.println(auth.isAuthenticated());

        List<String> elements = new ArrayList<>();
        Element element = new ElementImage(1, "IMAGE", "URL IMAGE");
        Element element2 = new ElementVideo(1, "VIDEO", "H264");
        elements.add(element.render());
        elements.add(element2.render());

        return elements;
    }

    @GetMapping("/hello/get-all")
    public List<Element> getAll() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getAuthorities());
        System.out.println(auth.isAuthenticated());

        List<JpaElement> jpaElements = jpaElementQueries.findAll();

        List<Element> elements = jpaToDomainAdapter.convert(jpaElements);

        for (Element element : elements) {
            System.out.println(element.toString());

        }

        return elements;
    }

    @GetMapping("/hello/create")
    public String createMock(@RequestParam(value = "name", defaultValue = "World") String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getPrincipal());
        System.out.println(auth.getAuthorities());
        System.out.println(auth.isAuthenticated());

        List<JpaElement> elements = new ArrayList<>();
        JpaPage jpaPage = new JpaPage();
        jpaPage.setId(1L);
        elements.add(new JpaElementImage(1, jpaPage, "IMAGE", "URL IMAGE"));
        elements.add(new JpaElementVideo(2, jpaPage, "VIDEO", "H264"));
        elements.add(new JpaElementImage(3, jpaPage, "IMAGE", "URL IMAGE 2"));
        elements.add(new JpaElementVideo(4, jpaPage, "VIDEO", "H264 2"));
        for (JpaElement jpaElement : elements) {
            jpaElementQueries.saveAndFlush(jpaElement);
        }

        return "Elements Created";
    }

    @GetMapping("/admin")
    public String admin(@RequestParam(value = "name", defaultValue = "World") String name) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(auth.getPrincipal());
        System.out.println(auth.getAuthorities());
        System.out.println(auth.isAuthenticated());

        return "Hello Admin";
    }
}
