package se.iths.kattis.labbappuser.web;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import se.iths.kattis.labbappuser.model.AppUser;
import se.iths.kattis.labbappuser.service.AppUserService;

import java.io.Serializable;

// http://localhost:8080/labb-appuser/login.xhtml
// http://localhost:8080/labb-appuser/chat.xhtml

@Named
@SessionScoped  // beanen lever hela användarsessionen, en instans per användarsession
@Getter
@Setter
public class AppUserLoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private AppUserService appUserService;

    private String username;
    private String password;
    private AppUser loggedInUser;

    // String returneras eftersom den används för navigation och avser en action-metod
    // returnvärdet = vilken sida ska visas efter metoden
    public String login() {
        AppUser user = appUserService.login(username, password);

        // om användaren finns i databasen, sparas user i sessionen (loggedInUser)
        if (user != null) {
            loggedInUser = user;
            // JSF navigerar till chat-sidan
            return "chat.xhtml?faces-redirect=true";
        }

        // felmeddelande sparas i <h:messages>
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Fel användarnamn eller lösenord", null));

        return null;  // login misslyckas= stanna på samma sida (login.xhtml)
    }

    public String logout() {
        loggedInUser = null;
        // JSF navigerar till login-sidan
        return "login.xhtml?faces-redirect=true";
    }
}
