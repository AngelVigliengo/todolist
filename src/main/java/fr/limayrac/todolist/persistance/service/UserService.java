package fr.limayrac.todolist.persistance.service;

import fr.limayrac.todolist.persistance.model.User;
import fr.limayrac.todolist.persistance.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(final Integer id){return userRepository.findById(id);}

    public Iterable<User> getUsers(){return userRepository.findAll();}

    public void deleteUser(final Integer id)
    {
        userRepository.deleteById(id);
    }

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

}
