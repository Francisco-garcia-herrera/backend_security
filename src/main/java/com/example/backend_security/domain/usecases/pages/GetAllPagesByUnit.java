package com.example.backend_security.domain.usecases.pages;

import java.util.List;

import com.example.backend_security.domain.entities.Page;
import com.example.backend_security.domain.repositories.PageRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class GetAllPagesByUnit {

    @Autowired
    PageRepository pageRepository;

    public List<Page> get(Long id) {
        List<Page> pages = pageRepository.getPagesByUnitId(id);
        return pages;
    }

}
