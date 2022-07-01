package com.example.backend_security.infrastucture.http.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_security.domain.entities.Page;

import com.example.backend_security.domain.usecases.pages.GetAllPages;
import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.http.httprestentities.PageHttpRestEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Lazy
@RestController
@RequestMapping(path = "/pages", consumes = { MediaType.APPLICATION_JSON_VALUE,
        MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
public class PageController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GetAllPages getAllPages;
    @Autowired
    private DomainToDtoAdapter domainToDtoAdapter;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        List<PageHttpRestEntity> body = new ArrayList<>();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        List<Page> pagesDomain = new ArrayList<>();

        pagesDomain = getAllPages.get();
        body = domainToDtoAdapter.convertPages(pagesDomain);

        toReturn = new ResponseEntity<>(body, status);
        return toReturn;
    }

}
