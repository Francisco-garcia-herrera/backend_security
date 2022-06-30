package com.example.backend_security.infrastucture.database.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.backend_security.infrastucture.database.entities.JpaPage;

@Repository
public interface JpaPageQueries extends JpaRepository<JpaPage, Long>, JpaSpecificationExecutor<JpaPage> {

}