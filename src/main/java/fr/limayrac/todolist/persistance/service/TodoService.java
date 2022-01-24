package fr.limayrac.todolist.persistance.service;

import fr.limayrac.todolist.persistance.model.Todo;
import fr.limayrac.todolist.persistance.repository.TodoRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    public Optional<Todo> getTodo(final Integer id){return todoRepository.findById(id);}

    public Iterable<Todo> getTodos(){return todoRepository.findAll();}

    public void deleteTodo(final Integer id)
    {
        todoRepository.deleteById(id);
    }

    public Todo saveTodo(Todo todo)
    {
        return todoRepository.save(todo);
    }

}
