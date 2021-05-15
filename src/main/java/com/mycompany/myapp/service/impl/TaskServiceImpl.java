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
                if(answer.toLowerCase().equals("masterchef") || answer.toLowerCase().equals("master chef"))
                {
                    returnValue = true;
                    taskDTO.setIsCompleted(true);
                    save(taskDTO);
                    TaskDTO newTask = new TaskDTO();
                    newTask.setTaskName("Spotkanie w dziczy");
                    newTask.setTaskDescription("Mój informator przekaże ci tajny kod, ale tylko wtedy gdy pomożesz mu rozpracować szyfr stosowany przez Ludzi nikczemnego Wandeldorfa. Być może dzięki waszej współpracy uda nam się przejąć ich tajną wiadomość. Lubi śledzie i ukrył się w dziczy. Gdzie w Lodzi znajde śledzie i drzewa?!");
                    newTask.setImageUrl("../../content/images/zadanie2.png");
                    newTask.setIsCompleted(false);
                    newTask.setUserId(userId);
                    newTask.setQuestionId(2L);
                    save(newTask);
                }
                break;
            case "2":
                if(answer.toLowerCase().equals("ampy"))
                {
                    returnValue = true;
                    taskDTO.setIsCompleted(true);
                    save(taskDTO);
                    TaskDTO newTask = new TaskDTO();
                    newTask.setTaskName("Zaglebiajac sie w mroczne tajemnice LDZ");
                    newTask.setTaskDescription("Gratulacje! Swietna robota! Udalo Ci sie przejac tajna wiadomosc agenta pracującego dla Wandeldorfa. Tylko co to moze oznaczac? To słowo? Numerki? Literki? Te wartości liczbowe coś oznaczają?");
                    newTask.setImageUrl("../../content/images/notka.png");
                    newTask.setIsCompleted(false);
                    newTask.setUserId(userId);
                    newTask.setQuestionId(3L);
                    save(newTask);
                }
                break;
            case "3":
                if(answer.toLowerCase().equals("liceum"))
                {
                    returnValue = true;
                    taskDTO.setIsCompleted(true);
                    save(taskDTO);
                    TaskDTO newTask = new TaskDTO();
                    newTask.setTaskName("Przeszlosc Wybranca");
                    newTask.setTaskDescription("To jest milowy krok w kwestii pokrzyżowania planów Wandeldorfa! Już wiemy, że szykuje coś związanego z liceum! Może chodzi o to, co robiła osoba z przepowiedni za czasów swojej nauki w liceum? Słuchaj uważnie, bo świta mi pewien pomysł. Na Bałutach znajdź budynek, w którym znajdowało się przedwojenne kino. Ponoć w czasie wojny pełniło ono funkcję synagogi i odprawiano w nim nabożeństwa. \n" +
                        "Po wojnie budynek powrócił na pewien czas do pierwotnej funkcji. \n" +
                        "Bardziej aktualne historie głoszą, że odkąd budynek przestał być kinem, organizowano tam liczne koncerty. Podczas tych wydarzeń młode kobiety pogrążone w szale zabawy i upojone alkoholem zarywały stoły. Może tam, dowiemy się czegoś więcej.");

                    newTask.setImageUrl("../../content/images/mapalodzi.png");
                    newTask.setIsCompleted(false);
                    newTask.setUserId(userId);
                    newTask.setQuestionId(4L);
                    save(newTask);
                }
                break;
            case "4":
                if(answer.toLowerCase().equals("malpa") || answer.toLowerCase().equals("małpa"))
                {
                    returnValue = true;
                    taskDTO.setIsCompleted(true);
                    save(taskDTO);
                    TaskDTO newTask = new TaskDTO();
                    newTask.setTaskName("Haslo Johnatana");
                    newTask.setTaskDescription("Małpa to ulubione zwierzątko Wandeldorfa, zakupił je gdzieś w Lodzi. Czyzby wlasnie tam planowal tajne spotkanie swojego bractwa? Hmmm, udalo mi sie zlokalizować jego człowieka, dane przesyłam kodem, by nie dostały się w niepowołane ręce. Użyłem szyfru naszego chemika, profesora Mendelejewa.");
                    newTask.setImageUrl("../../content/images/mende.png");
                    newTask.setIsCompleted(false);
                    newTask.setUserId(userId);
                    newTask.setQuestionId(5L);
                    save(newTask);
                }
                break;
            case "5":
                if(answer.toLowerCase().equals("racuchy"))
                {
                    returnValue = true;
                    taskDTO.setIsCompleted(true);
                    save(taskDTO);
                    TaskDTO newTask = new TaskDTO();
                    newTask.setTaskName("Gdzie jada Wandeldorf?");
                    newTask.setTaskDescription("Bingo, mój człowiek znajduje się w miejscu w którym Wandeldorf uwielbia zamawiać racuchy. Knajpie zalozonej przez standupera z Lodzi. Szybko! Pędź tam czym prędzej!");
                    newTask.setImageUrl("../../content/images/kurtyna.png");
                    newTask.setIsCompleted(false);
                    newTask.setUserId(userId);
                    newTask.setQuestionId(6L);
                    save(newTask);
                }
                break;

            case "6":
                if(answer.toLowerCase().equals("piotrkowska"))
                {
                    returnValue = true;
                    taskDTO.setIsCompleted(true);
                    save(taskDTO);
                    TaskDTO newTask = new TaskDTO();
                    newTask.setTaskName("Diablica");
                    newTask.setTaskDescription("\"Pomagiero Lucyfera!\n" +
                        "Jesteś już blisko, jednak zanim dotrzesz do końca, przeniesiesz się tam, gdzie wszystko się zaczęło. Przygotuj się na mentalną wycieczkę do czasów LOPŁ. Lata już nie te, także skup się, wytęż pamięć i zepnij poślady: przed Tobą nieBrzeska Krzyżówka! Rozwiązanie wskaże punkt, w którym zakończy się (?) Twoja krucjata. Niech moc Pomiotów i cytrynówki będzie z Tobą!\n" +
                        "Sroga Bździocha Molocha\"");



                    newTask.setImageUrl("../../content/images/diabel.png");
                    newTask.setIsCompleted(false);
                    newTask.setUserId(userId);
                    newTask.setQuestionId(7L);
                    save(newTask);
                }
                break;

            case "7":
                if(answer.toLowerCase().equals("krówka destylacyjna") || answer.toLowerCase().equals("krowka destylacyjna"))
                {
                    returnValue = true;
                    taskDTO.setIsCompleted(true);
                    save(taskDTO);
                    TaskDTO newTask = new TaskDTO();
                    newTask.setTaskName("Gratulacje");
                    newTask.setTaskDescription("Wszystko jest już jasne! Dzięki dowodom które zebrałaś, udało się zdemaskować Wandeldorfa! Wygląda na to, że to koniec przygody? ");



                    newTask.setImageUrl("../../content/images/zadanie3.png");
                    newTask.setIsCompleted(false);
                    newTask.setUserId(userId);
                    newTask.setQuestionId(8L);
                    save(newTask);
                }
                break;

            default:
        }
        return taskDTO;
    }
}
