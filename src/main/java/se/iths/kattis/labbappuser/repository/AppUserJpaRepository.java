package se.iths.kattis.labbappuser.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import se.iths.kattis.labbappuser.model.AppUser;

import java.util.List;

@ApplicationScoped
public class AppUserJpaRepository implements AppUserRepository {

    @PersistenceContext(unitName = "labbNeon_database")
    private EntityManager entityManager;

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


}
