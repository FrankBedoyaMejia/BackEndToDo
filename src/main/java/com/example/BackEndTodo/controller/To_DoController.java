package com.example.BackEndTodo.controller;

import com.example.BackEndTodo.Entity.To_Do;
import com.example.BackEndTodo.exception.BadRequestException;
import com.example.BackEndTodo.exception.ResourceNotFoundException;
import com.example.BackEndTodo.service.To_DoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todo")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.11:3000/"})
public class To_DoController {

    private final To_DoService to_doService;


    @Autowired

    public To_DoController(To_DoService to_doService) {
        this.to_doService = to_doService;
    }

    @GetMapping
    public ResponseEntity<List<To_Do>> searchAllTo_Do(){
        List<To_Do> to_dos = to_doService.searchAllTo_Do();
        return ResponseEntity.ok(to_dos);
    }


    @PostMapping
    public ResponseEntity<To_Do> toRegisterTo_Do(@RequestBody To_Do to_do){
        To_Do to_doCreated = to_doService.createTo_do(to_do);
        return ResponseEntity.status(HttpStatus.CREATED).body(to_doCreated);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deteleTo_DoById(@PathVariable Long id){
        to_doService.deleteTo_Do(id);
        return ResponseEntity.ok("The ToDo with id: " + id + " was successfully deleted");
    }


}
