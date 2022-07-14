package com.example.backend_security.infrastucture.adapter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.example.backend_security.domain.entities.Element;
import com.example.backend_security.domain.entities.ElementCarouselData;
import com.example.backend_security.domain.entities.Page;
import com.example.backend_security.domain.entities.Role;
import com.example.backend_security.domain.entities.Unit;
import com.example.backend_security.domain.entities.User;
import com.example.backend_security.infrastucture.database.entities.JpaElement;
import com.example.backend_security.infrastucture.database.entities.JpaElementCarouselData;
import com.example.backend_security.infrastucture.database.entities.JpaPage;
import com.example.backend_security.infrastucture.database.entities.JpaRole;
import com.example.backend_security.infrastucture.database.entities.JpaUnit;
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
        logger.info("Convert JpaElement to DomainElement");
        return object.mapToDomainReduced();
    }

    public Page convert(JpaPage object) {
        logger.info("Convert JpaPage to DomainPage");
        return Page.builder()
                .id(object.getId())
                .name(object.getName())
                .elements(convert(object.getElements()))
                .unit(object.getUnit().mapToDomainReduced())
                .build();
    }

    public Page convertReduced(JpaPage object) {
        logger.info("Convert JpaPage to DomainPage");
        return Page.builder()
                .id(object.getId())
                .name(object.getName())
                .build();
    }

    public List<Page> convertPages(List<JpaPage> objects) {
        if (objects == null)
            return null;
        return objects.stream().map(this::convert).collect(Collectors.toList());
    }

    public List<Page> convertPagesReduced(List<JpaPage> objects) {
        if (objects == null)
            return null;
        return objects.stream().map(this::convertReduced).collect(Collectors.toList());
    }

    public ElementCarouselData convert(JpaElementCarouselData object) {
        logger.info("Convert ElementCarouselData to ElementCarouselData");
        return ElementCarouselData.builder()
                .id(object.getId())
                .type(object.getType())
                .title(object.getTitle())
                .elementCarousel(object.getElementCarousel().mapToElementCarrousel())
                .build();
    }

    public List<ElementCarouselData> convertElementCarouselDatas(List<JpaElementCarouselData> objects) {
        if (objects == null)
            return null;
        return objects.stream().map(this::convert).collect(Collectors.toList());
    }

    public Unit convert(JpaUnit object) {
        logger.info("Convert JpaUnit to DomainUnit");
        return Unit.builder()
                .id(object.getId())
                .name(object.getName())
                .pages(convertPagesReduced(object.getPages()))
                .build();
    }

    public List<Unit> convertUnits(List<JpaUnit> objects) {
        if (objects == null)
            return null;
        return objects.stream().map(this::convert).collect(Collectors.toList());
    }

}
