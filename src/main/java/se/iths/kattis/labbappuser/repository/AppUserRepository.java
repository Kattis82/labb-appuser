package se.iths.kattis.labbappuser.repository;

import se.iths.kattis.labbappuser.model.AppUser;

import java.util.List;

public interface AppUserRepository {

    AppUser save(AppUser user);

    List<AppUser> findAll();
}
