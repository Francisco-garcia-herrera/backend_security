package com.example.backend_security.infrastucture.database.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.backend_security.domain.entities.User;
import com.example.backend_security.infrastucture.adapter.JpaToDomainAdapter;
import com.example.backend_security.infrastucture.database.entities.JpaUser;
import com.example.backend_security.infrastucture.database.queries.JpaUserQueries;

@Repository
@Transactional
public class JpaUserRepository {

    @Autowired
    private JpaToDomainAdapter jpaToDomainAdapter;

    @Autowired
    private JpaUserQueries jpaUserQueries;

    public User findByUsername(String username) {
        if (username == null)
            return null;

        Optional<JpaUser> jpaUserOptional = Optional.ofNullable(this.jpaUserQueries.findByUsername(username));
        User user = null;

        if (jpaUserOptional.isPresent()) {
            user = jpaToDomainAdapter.convert(jpaUserOptional.get());
        }
        return user;
    }
}
