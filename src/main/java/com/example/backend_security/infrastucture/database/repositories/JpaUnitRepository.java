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

    @Override
    public Unit save(Unit unit) {
        JpaUnit saved = null;
        try {
            JpaUnit jpaUnit = domainToJpaAdapter.convert(unit);
            saved = this.jpaUnitQueries.save(jpaUnit);
        } catch (Exception e) {
            System.out.println("Exception save JpaUnitRepository " + e);
        }
        return jpaToDomainAdapter.convert(saved);
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            return;
        try {
            this.jpaUnitQueries.deleteById(id);
        } catch (Exception e) {
            /* throw new T2cDataBaseException("Error deleting md ict element, " + e); */
            System.out.println("Exception delete JpaUnitRepository " + e);
        }
    }

}
