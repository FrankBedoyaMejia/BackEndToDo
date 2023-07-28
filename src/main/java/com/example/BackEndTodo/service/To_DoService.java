package com.example.BackEndTodo.service;

import com.example.BackEndTodo.Entity.To_Do;
import com.example.BackEndTodo.repository.To_DoRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class To_DoService {

    private static final Logger LOGGER = Logger.getLogger(To_DoService.class);

    private final To_DoRepository to_doRepository;

    @Autowired
    public To_DoService(To_DoRepository to_doRepository){this.to_doRepository = to_doRepository;}

    public To_Do createTo_do(To_Do to_do){
        LOGGER.info("To_Do was created. " + to_do.getId());
        To_Do to_doCreate = to_doRepository.save(to_do);
        return to_doCreate;
    }

    public List<To_Do> searchAllTo_Do(){
        LOGGER.info("All ToDo´s were searched. ");
        return to_doRepository.findAll();
    }

    public void deleteTo_Do(Long id){
        LOGGER.warn("The ToDo with ID has been removed: " + id);
        to_doRepository.deleteById(id);
    }


}
