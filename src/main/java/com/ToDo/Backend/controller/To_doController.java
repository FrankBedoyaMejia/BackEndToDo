package com.ToDo.Backend.controller;

import com.ToDo.Backend.service.To_doService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Todo")
@CrossOrigin(origins ={"http://localhost:3000", "http://127.0.0.1:3000/"} )
public class To_doController {

    private final To_doService toDoService;

    @Autowired
    public To_doController(To_doService toDoService) {
        this.toDoService = toDoService;
    }




}
