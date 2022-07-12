package com.example.backend_security.infrastucture.http.controllers;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_security.domain.entities.Unit;
import com.example.backend_security.domain.usecases.unit.CreateUnit;
import com.example.backend_security.domain.usecases.unit.GetAllUnits;
import com.example.backend_security.infrastucture.adapter.DomainToDtoAdapter;
import com.example.backend_security.infrastucture.http.httprestentities.UnitHttpRestEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Lazy
@RestController
@RequestMapping(path = "/units", consumes = { MediaType.APPLICATION_JSON_VALUE,
        MediaType.ALL_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
public class UnitController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private GetAllUnits getAllUnits;

    @Autowired
    private CreateUnit createUnit;

    @Autowired
    private DomainToDtoAdapter domainToDtoAdapter;

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {
        List<UnitHttpRestEntity> body = new ArrayList<>();
        HttpStatus status = HttpStatus.OK;
        ResponseEntity<?> toReturn;
        List<Unit> unitDomain = new ArrayList<>();

        unitDomain = getAllUnits.get();
        body = domainToDtoAdapter.convertUnits(unitDomain);

        toReturn = new ResponseEntity<>(body, status);
        return toReturn;
    }

    @PostMapping()
    public ResponseEntity<?> create(@RequestBody UnitHttpRestEntity data) {
        UnitHttpRestEntity body = null;
        Unit createdUnit = null;
        HttpStatus status = HttpStatus.CREATED;
        ResponseEntity<?> toReturn;
        try {
            // Long userId = getAuthUserId();
            Unit unit = data.mapToDomain(data);
            createdUnit = createUnit.create(unit);
            body = domainToDtoAdapter.convertUnitReduced(createdUnit);

        } catch (Exception e) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
            logger.error("Generic uncontrolled ERROR", e);
            return new ResponseEntity<>(e.getMessage(), status);
        } finally {
            toReturn = new ResponseEntity<>(body, status);
            logger.debug(". Status<" + status + ">");
        }
        return toReturn;
    }

}
