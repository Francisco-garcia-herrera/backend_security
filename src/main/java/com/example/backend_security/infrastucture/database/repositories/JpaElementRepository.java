package com.example.backend_security.infrastucture.database.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.repositories.ElementRepository;
import com.example.backend_security.infrastucture.adapter.DomainToJpaAdapter;
import com.example.backend_security.infrastucture.adapter.JpaToDomainAdapter;
import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.queries.JpaElementQueries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaElementRepository implements ElementRepository {

    @Autowired
    JpaElementQueries jpaElementQueries;

    @Autowired
    JpaToDomainAdapter jpaToDomainAdapter;

    @Autowired
    DomainToJpaAdapter domainToJpaAdapter;

    @Override
    public List<Element> getAll() {
        List<JpaElement> jpaElements = new ArrayList<>();
        try {
            jpaElements = jpaElementQueries.findAll();
        } catch (Exception e) {
            /* throw new Exception(); */
            System.out.println("Exception GetAll JpaElementRepository " + e);
        }
        return jpaToDomainAdapter.convert(jpaElements);
    }

    @Override
    public List<Element> renderAllByPage(Long pageId) {
        List<JpaElement> jpaElements = new ArrayList<>();
        try {
            jpaElements = jpaElementQueries.findByPageId(pageId);
        } catch (Exception e) {
            /* throw new Exception(); */
            System.out.println("Exception RenderAllByPage JpaElementRepository " + e);
        }
        return jpaToDomainAdapter.convert(jpaElements);
    }

    @Override
    public Element getElementById(Long elementId) {
        Optional<JpaElement> jpaElement = null;
        try {
            jpaElement = jpaElementQueries.findById(elementId);
        } catch (Exception e) {
            /* throw new Exception(); */
            System.out.println("Exception Get Element by Id JpaElementRepository " + e);
        }

        return jpaToDomainAdapter.convert(jpaElement.get());

    }

    @Override
    public Element save(Element element) {
        JpaElement saved = null;
        try {
            JpaElement jpaElement = domainToJpaAdapter.convert(element);
            saved = this.jpaElementQueries.save(jpaElement);
        } catch (Exception e) {
            System.out.println("Exception save JpaElementRepository " + e);
        }
        return jpaToDomainAdapter.convert(saved);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            return;
        try {
            this.jpaElementQueries.deleteById(id);
        } catch (Exception e) {
            /* throw new T2cDataBaseException("Error deleting md ict element, " + e); */
            System.out.println("Exception delete JpaElementRepository " + e);
        }
    }

    @Override
    public Element update(Element element) {
        if (element == null)
            return null;
        JpaElement saved = null;
        try {
            JpaElement jpaElement = domainToJpaAdapter.convert(element);
            saved = this.jpaElementQueries.saveAndFlush(jpaElement);
        } catch (Exception e) {
            /* throw new T2cDataBaseException("Error deleting md ict element, " + e); */
            System.out.println("Exception update JpaElementRepository " + e);
        }
        return jpaToDomainAdapter.convert(saved);
    }

}
