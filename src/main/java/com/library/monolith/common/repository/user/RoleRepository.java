package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {


    Optional<Role> findByName(String name);
}
