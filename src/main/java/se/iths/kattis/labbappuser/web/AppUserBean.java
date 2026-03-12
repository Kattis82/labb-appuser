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

// http://localhost:8080/labb-appuser/appusers.xhtml

@Named // gör beanen tillgänglig i xhtml
@ViewScoped  // beanen lever så länge sidan är öppen
@Getter
@Setter
// JSF-controllern
public class AppUserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AppUserService appUserService;
    private String username;
    private String password;

    public void saveUser() {
        // skapar en ny användare
        appUserService.saveUser(new AppUser(username, password));
    }

    // används av appusers.xhtml
    public List<AppUser> getUsers() {
        return appUserService.getAllUsers();
    }


}
