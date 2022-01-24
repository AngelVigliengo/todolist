package fr.limayrac.todolist.persistance.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Todo> todolist = new ArrayList<>();

    public User() {
    }

    public User(Integer id, String nom, String prenom, List<Todo> todolist) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.todolist = todolist;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", todolist=" + todolist +
                '}';
    }
}
