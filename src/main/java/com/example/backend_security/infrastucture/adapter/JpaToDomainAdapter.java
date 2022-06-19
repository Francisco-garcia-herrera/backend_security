package com.example.backend_security.infrastucture.adapter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.Role;
import com.example.backend_security.domain.entities.User;
import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaRole;
import com.example.backend_security.infrastucture.database.entities.JpaUser;

@Component
public class JpaToDomainAdapter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public User convert(JpaUser object) {
        if (object == null)
            return null;
        return User.builder()
                .id(object.getId())
                .name(object.getName())
                .username(object.getUsername())
                .password(object.getPassword())
                .roles(covertRoles(object.getRoles()))
                .build();
    }

    public Set<Role> covertRoles(Set<JpaRole> objects) {
        if (objects == null)
            return null;
        return objects.stream().map(this::convert).collect(Collectors.toSet());
    }

    public Role convert(JpaRole object) {
        if (object == null)
            return null;
        return Role.builder()
                .id(object.getId())
                .name(Role.Name.valueOf(object.getName().name()))
                .build();
    }

    public List<Element> convert(List<JpaElement> objects) {
        if (objects == null)
            return null;
        return objects.stream().map(this::convert).collect(Collectors.toList());
    }

    public Element convert(JpaElement object) {
        logger.info("Conver JpaElement to Domain Element");
        return object.mapToDomain(object);
    }

}
