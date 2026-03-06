package se.iths.kattis.labbappuser.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import se.iths.kattis.labbappuser.model.AppUser;
import se.iths.kattis.labbappuser.repository.AppUserRepository;

import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
public class AppUserService {

    private static final Logger LOGGER = Logger.getLogger(AppUserService.class.getName());
    private AppUserRepository appUserRepository;

    @Inject
    public AppUserService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    // tom konstruktor
    public AppUserService() {
    }


    @Transactional
    public AppUser saveUser(AppUser user) {
        LOGGER.info("saveUser()");

        return appUserRepository.save(user);
    }

    public List<AppUser> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();
        if (users.isEmpty()) {
            LOGGER.warning("The list of users is empty");
        } else {
            LOGGER.info("The list of users size: " + users.size());
        }
        return users;
    }
}
