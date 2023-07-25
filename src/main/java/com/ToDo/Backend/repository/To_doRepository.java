package com.ToDo.Backend.repository;

import com.ToDo.Backend.entity.To_do;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface To_doRepository extends JpaRepository<To_do,Long> {
    List<To_do> findByUser_idLike(Long user_id);
}
