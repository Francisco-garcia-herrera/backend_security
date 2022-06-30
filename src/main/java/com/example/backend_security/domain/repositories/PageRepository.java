package com.example.backend_security.domain.repositories;

import java.util.List;

import com.example.backend_security.domain.entities.Page;

public interface PageRepository {

    List<Page> getAll();

}
