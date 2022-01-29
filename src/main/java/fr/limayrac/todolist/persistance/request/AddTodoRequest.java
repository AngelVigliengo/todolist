package fr.limayrac.todolist.persistance.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddTodoRequest {

    private String title;
    private boolean done;


    public AddTodoRequest() {
    }

    public AddTodoRequest(String title, boolean done) {
        this.title = title;
        this.done = done;
    }
}
