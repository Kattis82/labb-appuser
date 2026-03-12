package se.iths.kattis.labbappuser.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import se.iths.kattis.labbappuser.model.AppUser;

import java.util.List;

@ApplicationScoped  // finns bara en enda instans av klassen i hela applikationen
public class AppUserJpaRepository implements AppUserRepository {

    @PersistenceContext(unitName = "labbNeon_database")
    // JPA:s verktyg för att prata med databasen
    private EntityManager entityManager;

    // sparar AppUser i databasen
    @Override
    public AppUser save(AppUser user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public List<AppUser> findAll() {
        return entityManager.createQuery(
                "SELECT u FROM AppUser u", AppUser.class).getResultList();
    }

    @Override
    public AppUser findByUsernameAndPassword(String username, String password) {

        List<AppUser> users = entityManager.createQuery(
                        // :username och :password = är parametrar
                        "SELECT u FROM AppUser u WHERE u.username = :username AND u.password = :password",
                        AppUser.class)
                .setParameter("username", username)  // sätter parametern username
                .setParameter("password", password)  // sätter parametern password
                .getResultList();

        // om listan är tom
        if (users.isEmpty()) {
            return null;
        }
        // login borde bara ge en användare
        // hämtar därför första elementet i listan
        return users.get(0);
    }

}
