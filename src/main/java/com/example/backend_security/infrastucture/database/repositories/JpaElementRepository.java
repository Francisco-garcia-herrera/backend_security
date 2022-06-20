package com.example.backend_security.infrastucture.database.repositories;

import java.util.ArrayList;
import java.util.List;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.repositories.ElementRepository;
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

    @Override
    public List<Element> getAll() {
        List<JpaElement> jpaElements = new ArrayList<>();
        try {
            jpaElements = jpaElementQueries.findAll();
        } catch (Exception e) {
            /* throw new Exception(); */
            System.out.println("Exception GetAll " + e);
        }
        return jpaToDomainAdapter.convert(jpaElements);
    }

}
