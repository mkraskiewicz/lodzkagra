package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TaskService;
import com.mycompany.myapp.domain.Task;
import com.mycompany.myapp.repository.TaskRepository;
import com.mycompany.myapp.service.dto.TaskDTO;
import com.mycompany.myapp.service.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Task}.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    public TaskServiceImpl(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskDTO save(TaskDTO taskDTO) {
        log.debug("Request to save Task : {}", taskDTO);
        Task task = taskMapper.toEntity(taskDTO);
        task = taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAll() {
        log.debug("Request to get all Tasks");
        return taskRepository.findAll().stream()
            .map(taskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TaskDTO> findAllOfUser(Long userId) {
        log.debug("Request to get all Tasks");
        return taskRepository.findAllByUserId(userId).stream()
            .map(taskMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<TaskDTO> findOne(Long id) {
        log.debug("Request to get Task : {}", id);
        return taskRepository.findById(id)
            .map(taskMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Task : {}", id);
        taskRepository.deleteById(id);
    }

    @Override
    public TaskDTO checkQuestion(TaskDTO taskDTO, String answer, Long userId) {
        boolean returnValue = false;
        switch(taskDTO.getQuestionId().toString()) {
            case "1":
                if(answer.toLowerCase().equals("test"))
                {
                    returnValue = true;
                    taskDTO.setIsCompleted(true);
                    save(taskDTO);
                    TaskDTO newTask = new TaskDTO();
                    newTask.setTaskName("Drugie Tajne Zadanie");
                    newTask.setTaskDescription("Tutaj jest opis drugiego super tajnego zadania.");
                    newTask.setImageUrl("https://upload.wikimedia.org/wikipedia/commons/c/c6/%C5%81%C3%B3d%C5%BA_-_rivers.svg");
                    newTask.setIsCompleted(false);
                    newTask.setUserId(userId);
                    newTask.setQuestionId(2L);
                    save(newTask);
                }
                break;
            case "2":
                if(answer.toLowerCase().equals("abc"))
                {
                    returnValue = true;
                    taskDTO.setIsCompleted(true);
                    save(taskDTO);
                    TaskDTO newTask = new TaskDTO();
                    newTask.setTaskName("Zaglebiajac sie w mroczne tajemnice LDZ");
                    newTask.setTaskDescription("\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"");
                    newTask.setImageUrl("https://www.transport-publiczny.pl/img/20190306101016RynekKobrowizUML.jpg_678-443.jpg");
                    newTask.setIsCompleted(false);
                    newTask.setUserId(userId);
                    newTask.setQuestionId(3L);
                    save(newTask);
                }
                break;
            case "3":
                if(answer.toLowerCase().equals("admin"))
                {
                    returnValue = true;
                    taskDTO.setIsCompleted(true);
                    save(taskDTO);
                    TaskDTO newTask = new TaskDTO();
                    newTask.setTaskName("Final");
                    newTask.setTaskDescription("\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"");
                    newTask.setImageUrl("https://lodz.travel/files/public/_processed_/2/5/csm_piotrkowska__29_of_31__66524abb36.jpg");
                    newTask.setIsCompleted(false);
                    newTask.setUserId(userId);
                    newTask.setQuestionId(4L);
                    save(newTask);
                }
                break;
            default:
        }
        return taskDTO;
    }
}
