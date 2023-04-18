package com.library.monolith.common.model.dto.user;

import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.Role;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import com.library.monolith.common.repository.user.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrationService {

    private final LibraryUserRepository libraryUserRepository;
    private final RoleRepository roleRepo;

    @Autowired
    public UserRegistrationService(LibraryUserRepository libraryUserRepository, RoleRepository roleRepo) {
        this.libraryUserRepository = libraryUserRepository;
        this.roleRepo = roleRepo;
    }

    public void registerDefaultUser(LibraryUser libraryUser) {
        Role roleUser = roleRepo.findByName("REGULAR");
        if (roleUser == null) {
            System.out.println("Role not found in the database.");
        } else {
            System.out.println("Role found: " + roleUser.getName());
        }

        libraryUser.addRole(roleUser);
        libraryUserRepository.save(libraryUser);

        System.out.println("User saved with roles: " + libraryUser.getRoles());
    }

}
