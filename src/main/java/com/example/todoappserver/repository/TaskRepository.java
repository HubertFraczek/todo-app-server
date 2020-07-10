package com.example.todoappserver.repository;

import com.example.todoappserver.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    @Query(value = "select * from todo_task t where t.id_user=?1", nativeQuery = true)
    List<Task> findAllById(Integer id);
}
