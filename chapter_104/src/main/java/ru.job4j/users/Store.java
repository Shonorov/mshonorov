package ru.job4j.users;

import java.util.List;
import java.util.Optional;

/**
 * Persistent layout interface for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Store {
    /**
     * Add user to store.
     * @param user to add.
     */
    void add(User user);

//    /**
//     * Add role to database.
//     * @param role to add.
//     */
//    void addRole(Role role);

    /**
     * Get all current roles.
     * @return list of roles.
     */
    List<Role> getRoles();

    /**
     * Update user in store.
     * @param user user to find.
     * @param update user to update.
     */
    void update(User user, User update);

    /**
     * Delete user from store.
     * @param user to delete.
     */
    void delete(User user);

    /**
     * Get all users from store.
     * @return list of users.
     */
    List<User> findAll();

    /**
     * Find User by its id.
     * @param id to search.
     * @return Optional of User.
     */
    Optional<User> findById(String id);

    /**
     * Find User by its login.
     * @param login to search.
     * @return Optional of User.
     */
    Optional<User> findByLogin(String login);

    /**
     * Find user with same credentials.
     * @param login to check.
     * @param password to check.
     * @return optional of user.
     */
    Optional<User> checkCredentials(String login, String password);
///////////////////////////////////////////////////////////////////////////////////////
    /**
     * Get list of countries from database.
     * @return list of all countries.
     */
    List<Country> getCountries();

    /**
     * Get list of cities from database.
     * @return list of cities.
     */
    List<City> getCities();

    /**
     * Get list of cities in one country.
     * @param name to find.
     * @return list of cities.
     */
    List<City> getCitiesByCountryName(String name);

    /**
     * Get country ID by name.
     * @param name to find.
     * @return country ID.
     */
    String getCountryID(String name);

    /**
     * Get city ID by name.
     * @param name to find.
     * @return city ID.
     */
    String getCityID(String name);

    /**
     * Get country name by ID.
     * @param id to find.
     * @return country name.
     */
    String getCountryName(String id);

    /**
     * Get city name by ID.
     * @param id to find.
     * @return city name.
     */
    String getCityName(String id);
}
