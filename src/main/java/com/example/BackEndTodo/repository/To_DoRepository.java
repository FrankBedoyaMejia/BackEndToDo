package com.example.BackEndTodo.repository;

import com.example.BackEndTodo.Entity.To_Do;
import org.springframework.data.jpa.repository.JpaRepository;

public interface To_DoRepository extends JpaRepository<To_Do,Long> {

}
