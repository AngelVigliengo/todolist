package fr.limayrac.todolist.persistance.controller;

import fr.limayrac.todolist.persistance.model.Todo;
import fr.limayrac.todolist.persistance.model.User;
import fr.limayrac.todolist.persistance.service.TodoService;
import fr.limayrac.todolist.persistance.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    private TodoService todoService;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ModelAndView findAllUser(){
        return new ModelAndView("userList","users", userService.getUsers());
    }

    @GetMapping("/user/{userId}/todos")
    public ModelAndView viewAllTodoByUserId(@PathVariable("userId") Integer userId){
        User user = userService.getUser(userId).orElseThrow(() -> new NoSuchElementException());
        List<Todo> todolist = new ArrayList<>();
        if (!user.getTodolist().isEmpty()){
            todolist=user.getTodolist();
        }
        return new ModelAndView("todoList","todos", todolist);
    }
    @GetMapping("/user/todos")
    public ModelAndView findAllTodoByUserId(User user){
        List<Todo> todolist = new ArrayList<>();
        if (!user.getTodolist().isEmpty()){
            todolist=user.getTodolist();
        }
        return new ModelAndView("todoList","todos", todolist);
    }

    @GetMapping("/user")
    public ModelAndView detailUser(@RequestParam Integer userId) {
        Optional<User> user = userService.getUser(userId);
        return new ModelAndView("editUser", "user", user.orElse(null));
    }

    @GetMapping("/user/create")
    public ModelAndView createUser() {
        User User= new User();
        return new ModelAndView("createUser", "user", User);
    }

    @PostMapping("/user/create")
    public ModelAndView submitUser(@ModelAttribute("user") User user, ModelMap model) {
        model.addAttribute("nom", user.getNom());
        model.addAttribute("prenom", user.getPrenom());
        userService.saveUser(user);

        return findAllUser();
    }

    @PostMapping("/user/{userId}/delete")
    public ModelAndView deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return findAllUser();
    }

    @GetMapping("/user/{userId}/edit")
    public ModelAndView editUser(@PathVariable("userId") Integer userId) {
        User user = userService.getUser(userId).orElseThrow(() -> new NoSuchElementException());
        return new ModelAndView("editUser", "user", user);
    }

    @PostMapping("/user/{userId}/edit")
    public ModelAndView submitEditUser(@PathVariable("userId") Integer userId,@ModelAttribute("user") User user,ModelMap model){
        user.setId(userId);
        model.addAttribute("nom", user.getNom());
        model.addAttribute("prenom", user.getPrenom());
        userService.saveUser(user);

        return findAllUser();
    }

    @GetMapping("/user/{userId}/todo/create")
    public ModelAndView createTodo(@PathVariable("userId") Integer userId) {
        Todo todo= new Todo();
        todo.setUserId(userId);
        return new ModelAndView("createTodo", "todo", todo);
    }

    @PostMapping("/user/{userId}/todo/create")
    public ModelAndView submitTodo(@PathVariable("userId") Integer userId, @ModelAttribute("todo") Todo todo, ModelMap model) {
        User user = userService.getUser(userId).orElseThrow(() -> new NoSuchElementException());
        model.addAttribute("title", todo.getTitle());
        model.addAttribute("description", todo.getDescription());
        model.addAttribute("done", todo.isDone());
        user.getTodolist().add(todo);
        userService.saveUser(user);
        return findAllTodoByUserId(user);
    }

    @PostMapping("/user/{userId}/todo/{todoId}/delete")
    public ModelAndView deleteTodo(@PathVariable("userId") Integer userId,@PathVariable("todoId") Integer todoId){
        User user = userService.getUser(userId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = todoService.getTodo(todoId).orElseThrow(() -> new NoSuchElementException());
        user.getTodolist().remove(todo);
        todoService.deleteTodo(todo.getId());
        return findAllTodoByUserId(user);
    }

    @GetMapping("/user/{userId}/todo/{todoId}/edit")
    public ModelAndView editTodo(@PathVariable("userId") Integer userId,@PathVariable("todoId") Integer todoId) {
        Todo todo = todoService.getTodo(todoId).orElseThrow(() -> new NoSuchElementException());

        return new ModelAndView("editTodo", "todo", todo);
    }

    @PostMapping("/user/{userId}/todo/{todoId}/edit")
    public ModelAndView submitEditTodo(@PathVariable("userId") Integer userId,@PathVariable("todoId") Integer todoId,@ModelAttribute("todo") Todo todo,ModelMap model){
        User user = userService.getUser(userId).orElseThrow(() -> new NoSuchElementException());
        model.addAttribute("title", todo.getTitle());
        model.addAttribute("description", todo.getDescription());
        model.addAttribute("done", todo.isDone());
        List<Todo> todoList = user.getTodolist();

        for (int i=0;i<todoList.size();i++){
            if (todoList.get(i).getId()==todoId)
            {
                todoList.set(i,todo);
            }
        }
        user.setTodolist(todoList);
        userService.saveUser(user);

        return findAllTodoByUserId(user);
    }

}