package pl.camp.it.rent.database.imp.memory;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Repository;
import pl.camp.it.rent.database.IUserDAO;
import pl.camp.it.rent.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UserDatabase implements IUserDAO {
    private final List<User> users =new ArrayList<>();



    public UserDatabase() {
        this.users.add(new User(
                1,
                "Karol",
                "Kowalski",
                "admin", DigestUtils.md5Hex("admin"),
                "kkowalski@gmail.com"));
        this.users.add(new User(
                2,
                "Kamil",
                "Nowak",
                "pasword",DigestUtils.md5Hex("password"),
                "knowak@gmail.com"));

    }

    @Override
    public Optional<User> getUserByLogin(String login){
        for(User user:this.users){
            if(user.getLogin().equals(login)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }
    @Override
    public void addUser(User user){
        this.users.add(user);
    }

}
/*

    public UserDatabase() {
        this.users.add(new User(
                        1,
                        "Karol",
                        "Kowalski",
                        "admin",
                        DigestUtils.md5Hex("admin"),
                        "kkowalski@gmail.com"));
        this.users.add(new User(
                        2,
                        "Janusz",
                        "Malinowski",
                        "janusz",
                        DigestUtils.md5Hex("janusz"),
                        "jmalinowski@gmail.com"));
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
}
 */
