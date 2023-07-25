package com.ToDo.Backend.service;

import com.ToDo.Backend.entity.To_do;
import com.ToDo.Backend.repository.To_doRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class To_doService {

    private static final Logger LOGGER = Logger.getLogger(To_doService.class);

    private final To_doRepository toDoRepository;
    @Autowired
    public To_doService(To_doRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    public To_do createTo_Do(To_do toDo){
        LOGGER.info("To_Do was created: " + toDo.getId());
        return toDoRepository.save(toDo);
    }

    public List<To_do> searchAllBooking(){
        LOGGER.info("All To_Do´s were searched: ");
        return toDoRepository.findAll();
    }

    public Optional<To_do> searchToDo(Long id){
        LOGGER.info("a ToDo with ID was searched: "+id);
        return toDoRepository.findById(id);
    }


    public List<To_do> searchToDoByUser(Long id){
        LOGGER.info("The user´s ToDo with ID " + id + " were searched: ");
        return toDoRepository.findByUser_idLike(id);
    }

    public void deleteToDo(Long id){
        LOGGER.info("The ToDo with ID has been removed: "+id);
        toDoRepository.deleteById(id);
    }
}
