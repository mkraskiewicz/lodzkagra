package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Task;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Task entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    public List<Task> findAllByUserId(Long userId);
}
