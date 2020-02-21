package com.code.aeon.repository;

import com.code.aeon.model.Role;
import com.code.aeon.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Optional<Role>findByName (RoleName roleName);

}
