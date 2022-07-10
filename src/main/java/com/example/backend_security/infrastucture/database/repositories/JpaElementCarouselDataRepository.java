package com.example.backend_security.infrastucture.database.repositories;

import java.util.Optional;

import com.example.backend_security.domain.entities.ElementCarouselData;
import com.example.backend_security.domain.repositories.ElementCarouselDataRepository;
import com.example.backend_security.infrastucture.adapter.DomainToJpaAdapter;
import com.example.backend_security.infrastucture.adapter.JpaToDomainAdapter;
import com.example.backend_security.infrastucture.database.entities.JpaElementCarouselData;
import com.example.backend_security.infrastucture.database.queries.JpaElementCarouselDataQueries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaElementCarouselDataRepository implements ElementCarouselDataRepository {

    @Autowired
    JpaElementCarouselDataQueries jpaElementCarouselDataQueries;

    @Autowired
    JpaToDomainAdapter jpaToDomainAdapter;

    @Autowired
    DomainToJpaAdapter domainToJpaAdapter;

    @Override
    public ElementCarouselData getById(Long elementCarouselDataId) {
        Optional<JpaElementCarouselData> jpaElementCarouselData = null;
        try {
            jpaElementCarouselData = jpaElementCarouselDataQueries.findById(elementCarouselDataId);
        } catch (Exception e) {
            /* throw new Exception(); */
            System.out.println("Exception Get Element by Id JpaElementRepository " + e);
        }

        return jpaToDomainAdapter.convert(jpaElementCarouselData.get());

    }

    @Override
    public ElementCarouselData save(ElementCarouselData elementCarouselData) {
        JpaElementCarouselData saved = null;
        try {
            JpaElementCarouselData jpaElementCarouselData = domainToJpaAdapter.convert(elementCarouselData);
            saved = this.jpaElementCarouselDataQueries.save(jpaElementCarouselData);
        } catch (Exception e) {
            System.out.println("Exception save JpaElementRepository " + e);
        }
        return jpaToDomainAdapter.convert(saved);
    }

}
