package com.saper.clinicalotus.model;

import com.saper.clinicalotus.enums.RoleNames;
import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Enumerated(EnumType.STRING)
    RoleNames role;

    @ManyToMany(mappedBy = "roles")
    Set<Funcionario> funcionarios;

    @Override
    public String getAuthority() {
        return role.toString();
    }
}
