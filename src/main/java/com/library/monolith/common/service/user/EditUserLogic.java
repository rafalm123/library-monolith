package com.library.monolith.common.service.user;

import com.library.monolith.common.model.dto.user.LibraryUserUpdateDTO;
import com.library.monolith.common.model.entity.user.Address;
import com.library.monolith.common.model.entity.user.LibraryUser;
import com.library.monolith.common.model.entity.user.LibraryUserVersion;
import com.library.monolith.common.repository.user.LibraryUserRepository;
import com.library.monolith.common.repository.user.LibraryUserVersionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@AllArgsConstructor
@NoArgsConstructor
public class EditUserLogic {

    private LibraryUserRepository libraryUserRepository;
    private LibraryUserVersionRepository libraryUserVersionRepository;

    public void editUser(String username, LibraryUserUpdateDTO libraryUserUpdateDTO){

        LibraryUser libraryUser = libraryUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        LibraryUserVersion latestVersion = libraryUser.getLatestVersion();
        LibraryUserVersion newVersion = new LibraryUserVersion();
        Address newAddress = new Address();
        Address latestAddress = latestVersion.getAddress();


        if (libraryUserUpdateDTO.getName() == null){
            newVersion.setName(latestVersion.getName());
        } else {
            newVersion.setName(libraryUserUpdateDTO.getName());
        }
        if (libraryUserUpdateDTO.getSurname() == null){
            newVersion.setSurname(latestVersion.getSurname());
        }else {
            newVersion.setSurname(libraryUserUpdateDTO.getSurname());
        }
        if (libraryUserUpdateDTO.getNickname() == null){
            newVersion.setNickname(latestVersion.getNickname());
        }else {
            newVersion.setNickname(libraryUserUpdateDTO.getNickname());
        }
        if (libraryUserUpdateDTO.getEmail()==null){
            newVersion.setEmail(latestVersion.getEmail());
        } else {
            newVersion.setEmail(libraryUserUpdateDTO.getEmail());
        }


        if (libraryUserUpdateDTO.getStreet() == null){
            newAddress.setStreet(latestAddress.getStreet());
        }else {
            newAddress.setStreet(libraryUserUpdateDTO.getStreet());
        }
        if (libraryUserUpdateDTO.getCity() == null){
            newAddress.setCity(latestAddress.getCity());
        }else {
            newAddress.setCity(libraryUserUpdateDTO.getCity());
        }
        if (libraryUserUpdateDTO.getPostalCode() == null){
            newAddress.setPostalCode(latestAddress.getPostalCode());
        }else {
            newAddress.setPostalCode(libraryUserUpdateDTO.getPostalCode());
        }
        if (libraryUserUpdateDTO.getCountry() == null){
            newAddress.setCountry(latestAddress.getCountry());
        }else {
            newAddress.setCountry(libraryUserUpdateDTO.getCountry());
        }
        if (libraryUserUpdateDTO.getState() == null){
            newAddress.setState(latestAddress.getState());
        }else {
            newAddress.setState(libraryUserUpdateDTO.getState());
        }

        newVersion.setAddress(newAddress);
        newVersion.setLibraryUser(libraryUser);
        libraryUserVersionRepository.save(newVersion);

    }

}
