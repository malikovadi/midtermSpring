package com.kg.alatoo.midtermSpring.repositories;

import com.kg.alatoo.midtermSpring.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRolesRepository extends CrudRepository<Roles,String >, JpaRepository<Roles,String> {
    Optional<Roles> findByRoleName(Roles.Name roleName);
}

