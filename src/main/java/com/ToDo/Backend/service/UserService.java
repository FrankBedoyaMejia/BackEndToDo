package com.ToDo.Backend.service;

import com.ToDo.Backend.entity.User;
import com.ToDo.Backend.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class UserService { private static final Logger LOGGER = Logger.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User userDTO){
        LOGGER.info("User was created: "+userDTO.getId());
        return userRepository.save(userDTO);
    }

    public List<User> searchAllUser(){
        LOGGER.info("All users were searched: ");
        return userRepository.findAll();

    }

    public Optional<User> searchUser(Long id){
        LOGGER.info("A user with ID was searched: "+ id);
        return userRepository.findById(id);

    }

    public void updateUser(User user){
        LOGGER.warn("The user with ID "+user.getId()+" has been updated");
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        LOGGER.warn("The user with ID has been removed: "+id);
        userRepository.deleteById(id);
    }


    public Optional<User> validateUser(String email){
        return userRepository.findByEmail(email);
    }
}
