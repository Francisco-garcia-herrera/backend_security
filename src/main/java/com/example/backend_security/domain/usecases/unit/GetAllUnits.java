package com.example.backend_security.domain.usecases.unit;

import java.util.List;

import com.example.backend_security.domain.entities.Unit;
import com.example.backend_security.domain.repositories.UnitRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GetAllUnits {

    @Autowired
    UnitRepository unitRepository;

    public List<Unit> get() {
        List<Unit> units = unitRepository.getAll();
        return units;
    }

}
