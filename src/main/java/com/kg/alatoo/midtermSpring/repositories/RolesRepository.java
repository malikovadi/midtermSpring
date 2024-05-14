package com.kg.alatoo.midtermSpring.repositories;

import com.kg.alatoo.midtermSpring.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RolesRepository extends CrudRepository<Roles, Roles.Name>,
        JpaRepository<Roles, Roles.Name> {

}