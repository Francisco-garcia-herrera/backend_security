package com.example.backend_security.infrastucture.database.repositories;

import com.example.backend_security.domain.entities.Unit;
import com.example.backend_security.domain.repositories.UnitRepository;
import com.example.backend_security.infrastucture.adapter.DomainToJpaAdapter;
import com.example.backend_security.infrastucture.adapter.JpaToDomainAdapter;
import com.example.backend_security.infrastucture.database.entities.JpaUnit;
import com.example.backend_security.infrastucture.database.queries.JpaUnitQueries;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaUnitRepository implements UnitRepository {

    @Autowired
    JpaUnitQueries jpaUnitQueries;

    @Autowired
    JpaToDomainAdapter jpaToDomainAdapter;

    @Autowired
    DomainToJpaAdapter domainToJpaAdapter;

    @Override
    public List<Unit> getAll() {
        List<JpaUnit> jpaUnits = new ArrayList<>();
        try {
            jpaUnits = jpaUnitQueries.findAll();
        } catch (Exception e) {
            /* throw new Exception(); */
            System.out.println("Exception GetAll JpaElementRepository " + e);
        }
        return jpaToDomainAdapter.convertUnits(jpaUnits);
    }

}
