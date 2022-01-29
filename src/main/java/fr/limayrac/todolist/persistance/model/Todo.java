package fr.limayrac.todolist.persistance.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "todo")
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", length = 50)
    private String title;

    @Column(name = "done")
    private boolean done;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "description")
    private String description;

    public Todo() {
    }
}
