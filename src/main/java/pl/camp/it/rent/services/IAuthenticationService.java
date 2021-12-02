package pl.camp.it.rent.services;

import pl.camp.it.rent.model.view.RgisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
     void logout();
    boolean register(RgisterUser rgisterUser);
}
