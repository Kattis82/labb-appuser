package se.iths.kattis.labbappuser.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import se.iths.kattis.labbappuser.model.AppUser;
import se.iths.kattis.labbappuser.repository.AppUserRepository;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped // en enda instans i applikationen som alla använder
public class AppUserService {

    // skapa en Logger för att kunna logga
    private static final Logger LOGGER = Logger.getLogger(AppUserService.class.getName());
    private AppUserRepository appUserRepository;

    // CDI - Glassfish skapar objektet automatiskt
    @Inject
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    // tom konstruktor
    public AppUserService() {
    }


    // starta en databastransaktion - om något går fel -> rollback
    @Transactional
    public AppUser saveUser(AppUser user) {
        LOGGER.info("saveUser()");

        return appUserRepository.save(user);
    }


    public List<AppUser> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();
        if (users.isEmpty()) {
            LOGGER.warning("The list of users is empty");  // om listan är tom = varning
        } else {
            LOGGER.info("The list of users size: " + users.size()); // annars loggas antal
        }
        return users;
    }

    public AppUser login(String username, String password) {
        LOGGER.info("Attempt to login for user: " + username);
        AppUser user = appUserRepository.findByUsernameAndPassword(username, password);

        // om användaren finns ge info om lyckad inloggning
        if (user != null) {
            LOGGER.info("Login successful for user: " + username);
            // annars varning, att inloggning misslyckades
        } else {
            LOGGER.warning("Login failed for user: " + username);
        }

        return user;
    }
}
