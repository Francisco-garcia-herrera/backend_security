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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_security.domain.entities.Page;
import com.example.backend_security.domain.usecases.pages.CreatePage;
import com.example.backend_security.domain.usecases.pages.DeletePage;
import com.example.backend_security.domain.usecases.pages.GetAllPages;
import com.example.backend_security.domain.usecases.pages.GetPage;
import com.example.backend_security.domain.usecases.pages.UpdatePage;
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
    private GetPage getPage;

    @Autowired
    private CreatePage createPage;

    @Autowired
    private DeletePage deletePage;

    @Autowired
    private UpdatePage updatePage;

    @Autowired
    private DomainToDtoAdapter domainToDtoAdapter;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        List<PageHttpRestEntity> body = new ArrayList<>();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        List<Page> pagesDomain = new ArrayList<>();

        pagesDomain = getAllPages.get();
        body = domainToDtoAdapter.convertPagesReduced(pagesDomain);

        toReturn = new ResponseEntity<>(body, status);
        return toReturn;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        PageHttpRestEntity body = new PageHttpRestEntity();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        Page pageDomain = new Page();

        pageDomain = getPage.get(id);
        body = domainToDtoAdapter.convertPage(pageDomain);

        toReturn = new ResponseEntity<>(body, status);
        return toReturn;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody PageHttpRestEntity data) {
        PageHttpRestEntity body = null;
        Page createdPage = null;
        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<?> toReturn;
        try {
            // Long userId = getAuthUserId();
            Page page = data.mapToDomain(data);
            createdPage = createPage.create(page);
            body = domainToDtoAdapter.convertPage(createdPage);

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
    public ResponseEntity<?> delete(@PathVariable Long id) {
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        try {
            deletePage.delete(id);
        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Generic uncontrolled ERROR", e);
            return new ResponseEntity<>(e.getMessage(), status);
        } finally {
            toReturn = new ResponseEntity<>("Page succesfully deleted", status);
            logger.debug(". Status<" + status + ">");
        }
        return toReturn;
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody PageHttpRestEntity data) {
        PageHttpRestEntity body = null;
        Page savedPage = null;
        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<?> toReturn = null;
        try {
            Page page = data.mapToDomain(data);
            savedPage = updatePage.update(page);
            body = domainToDtoAdapter.convertPage(savedPage);
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
