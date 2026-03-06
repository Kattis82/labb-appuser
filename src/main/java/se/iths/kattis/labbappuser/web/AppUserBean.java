package se.iths.kattis.labbappuser.web;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import se.iths.kattis.labbappuser.model.AppUser;
import se.iths.kattis.labbappuser.service.AppUserService;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Getter
@Setter
public class AppUserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AppUserService appUserService;
    private String username;
    private String password;

    public void saveUser() {
        appUserService.saveUser(new AppUser(username, password));
    }

    public List<AppUser> getUsers() {
        return appUserService.getAllUsers();
    }


}
