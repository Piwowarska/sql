package pl.camp.it.rent.model.view;

import pl.camp.it.rent.model.User;

public class RgisterUser extends User {
    private String password2;

    public String getPassword2() {
        return password2;
    }

    public void setPassword2(String password2) {
        this.password2 = password2;
    }
}
