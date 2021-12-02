package pl.camp.it.rent.database;

import pl.camp.it.rent.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserDAO {
   // List<User> getUsers();
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
}
