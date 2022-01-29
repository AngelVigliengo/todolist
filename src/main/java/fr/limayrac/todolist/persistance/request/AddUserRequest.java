package fr.limayrac.todolist.persistance.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddUserRequest {

    private String nom;
    private String prenom;

    public AddUserRequest(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }
}
