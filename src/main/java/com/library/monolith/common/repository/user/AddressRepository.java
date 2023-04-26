package com.library.monolith.common.repository.user;

import com.library.monolith.common.model.entity.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
