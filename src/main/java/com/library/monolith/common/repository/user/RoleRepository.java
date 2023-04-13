package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("select r from Role r where r.name = ?1")
    Role findByName(String name);

}
