package fr.limayrac.todolist.persistance.repository;

import fr.limayrac.todolist.persistance.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
