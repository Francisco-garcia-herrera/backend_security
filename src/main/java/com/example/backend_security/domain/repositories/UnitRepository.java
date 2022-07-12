package com.example.backend_security.domain.repositories;

import java.util.List;

import com.example.backend_security.domain.entities.Unit;

public interface UnitRepository {

    List<Unit> getAll();

    Unit save(Unit unit);
}
