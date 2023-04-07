package com.library.monolith.common.mapping.user;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityMapper {

    private LibraryUserRepository libraryUserRepository;
    private ObjectMapper mapper;
    private PasswordEncoder passwordEncoder;


    public void saveUserFromJson() throws IOException {
        try (InputStream inputStream = new FileInputStream("C:\\Users\\Dell\\IdeaProjects\\library\\src\\main\\resources\\static\\users.json")) {
            UserWrapper userWrapper = mapper.readValue(
                    inputStream,
                    UserWrapper.class);

            List<LibraryUser> users = userWrapper.getUsers();

            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            users.forEach(libraryUser -> {
                    libraryUser.setPassword(passwordEncoder.encode(libraryUser.getPassword()));
                    libraryUser.getLibraryUserVersions()
                                .forEach(libraryUserVersion -> {
                                    libraryUserVersion.setLibraryUser(libraryUser);
                                    libraryUserVersion.getAddress().setLibraryUserVersion(libraryUserVersion);
                                });
                    }
            );
            libraryUserRepository.saveAll(users);
        }
    }
}

