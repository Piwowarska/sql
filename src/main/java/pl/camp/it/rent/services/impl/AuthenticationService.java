package pl.camp.it.rent.services.impl;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.camp.it.rent.database.IUserDAO;
import pl.camp.it.rent.model.User;
import pl.camp.it.rent.model.view.RgisterUser;
import pl.camp.it.rent.services.IAuthenticationService;
import pl.camp.it.rent.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class AuthenticationService implements IAuthenticationService {

    @Autowired
    IUserDAO userDatabase;

    @Resource
    SessionObject sessionObject;

    @Override
    public void authenticate(String login, String password) {
        Optional<User> userBox = this.userDatabase.getUserByLogin(login);
        if (userBox.isPresent() && userBox.get().getPassword().equals(DigestUtils.md5Hex(password))) {
            this.sessionObject.setUser(userBox.get());

        }
    }
    @Override
    public void logout() {
        this.sessionObject.setUser(null);
    }

    @Override
    public boolean register(RgisterUser rgisterUser) {
        if (!this.userDatabase.getUserByLogin(rgisterUser.getLogin()).isPresent()) {
            rgisterUser.setPassword(DigestUtils.md5Hex(rgisterUser.getPassword()));
            this.userDatabase.addUser(rgisterUser);
            return true;
        }
        return false;
    }
}

