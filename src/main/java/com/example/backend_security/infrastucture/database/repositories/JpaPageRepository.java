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

    @Override
    public List<Page> getPagesByUnitId(Long unitId) {
        List<JpaPage> jpaPages = new ArrayList<>();
        try {
            jpaPages = jpaPageQueries.findByUnitId(unitId);
        } catch (Exception e) {
            /* throw new Exception(); */
            System.out.println("Exception GetAll JpaElementRepository " + e);
        }
        return jpaToDomainAdapter.convertPages(jpaPages);
    }

    @Override
    public Page save(Page page) {
        JpaPage saved = null;
        try {
            JpaPage jpaPage = domainToJpaAdapter.convert(page);
            saved = this.jpaPageQueries.save(jpaPage);
        } catch (Exception e) {
            System.out.println("Exception save JpaPageRepository " + e);
        }
        return jpaToDomainAdapter.convert(saved);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            return;
        try {
            this.jpaPageQueries.deleteById(id);
        } catch (Exception e) {
            /* throw new T2cDataBaseException("Error deleting md ict element, " + e); */
            System.out.println("Exception delete JpaPageRepository " + e);
        }
    }

    @Override
    public Page update(Page page) {
        if (page == null)
            return null;
        JpaPage saved = null;
        try {
            JpaPage jpaPage = domainToJpaAdapter.convert(page);
            saved = this.jpaPageQueries.saveAndFlush(jpaPage);
        } catch (Exception e) {
            /* throw new T2cDataBaseException("Error deleting md ict element, " + e); */
            System.out.println("Exception update JpaElementRepository " + e);
        }
        return jpaToDomainAdapter.convert(saved);
    }

}
