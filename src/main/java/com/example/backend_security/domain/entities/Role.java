package com.example.backend_security.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Role {
    private Long id;
    private Name name;

    public enum Name {
        ROLE_USER,
        ROLE_ADMIN
    }

}
