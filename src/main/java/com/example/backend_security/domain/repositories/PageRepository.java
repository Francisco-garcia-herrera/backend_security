package com.example.backend_security.domain.repositories;

import java.util.List;

import com.example.backend_security.domain.entities.Page;

public interface PageRepository {

    List<Page> getAll();

    Page getPageById(Long pageId);

    Page save(Page page);

    void delete(Long id);

    Page update(Page page);
}
