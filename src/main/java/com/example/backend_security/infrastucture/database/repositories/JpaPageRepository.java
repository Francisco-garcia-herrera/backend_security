package com.example.backend_security.infrastucture.database.repositories;

import com.example.backend_security.domain.entities.Page;
import com.example.backend_security.domain.repositories.PageRepository;
import com.example.backend_security.infrastucture.adapter.DomainToJpaAdapter;
import com.example.backend_security.infrastucture.adapter.JpaToDomainAdapter;
import com.example.backend_security.infrastucture.database.entities.JpaPage;
import com.example.backend_security.infrastucture.database.queries.JpaPageQueries;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaPageRepository implements PageRepository {

    @Autowired
    JpaPageQueries jpaPageQueries;

    @Autowired
    JpaToDomainAdapter jpaToDomainAdapter;

    @Autowired
    DomainToJpaAdapter domainToJpaAdapter;

    @Override
    public List<Page> getAll() {
        List<JpaPage> jpaPages = new ArrayList<>();
        try {
            jpaPages = jpaPageQueries.findAll();
        } catch (Exception e) {
            /* throw new Exception(); */
            System.out.println("Exception GetAll JpaElementRepository " + e);
        }
        return jpaToDomainAdapter.convertPages(jpaPages);
    }

    @Override
    public Page getPageById(Long pageId) {
        Optional<JpaPage> jpaPageOptional = null;
        Page page = new Page();
        try {
            jpaPageOptional = jpaPageQueries.findById(pageId);
        } catch (Exception e) {
            /* throw new Exception(); */
            System.out.println("Exception GetAll JpaElementRepository " + e);
        }
        if (jpaPageOptional.isPresent()) {
            page = jpaToDomainAdapter.convert(jpaPageOptional.get());
        }
        return page;
    }

}
