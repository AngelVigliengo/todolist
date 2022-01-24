package fr.limayrac.todolist.persistance.controller;

import fr.limayrac.todolist.persistance.model.Todo;
import fr.limayrac.todolist.persistance.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping("/todo/list")
    public ModelAndView findAllTodo(){
        return new ModelAndView("todoList","todos", todoService.getTodos());
    }

    @GetMapping("/todo/list/{id}")
    public ModelAndView detailTodo(@PathVariable("id") final Integer id) {
        Optional<Todo> todo = todoService.getTodo(id);
        return new ModelAndView("detailTodo", "client", todo.orElse(null));
    }

    @GetMapping("/todo/create")
    public ModelAndView createTodo() {
        Todo todo= new Todo();
        return new ModelAndView("createTodo", "todo", todo);
    }

    @PostMapping("/todo/create")
    public ModelAndView submit(@ModelAttribute("client") Todo todo, ModelMap model) {
        model.addAttribute("title", todo.getTitle());
        todoService.saveTodo(todo);

        return findAllTodo();
    }
}
