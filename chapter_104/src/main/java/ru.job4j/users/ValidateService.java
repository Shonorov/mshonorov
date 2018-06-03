package ru.job4j.users;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Logic layout for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class ValidateService {
    /**
     * ValidateService singleton instance.
     * MemoryStore instance.
     */
    private static final ValidateService INSTANCE = new ValidateService();
    private final Store store = MemoryStore.getInstance();

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    /**
     * Check if user exists and add it.
     * @param user to check.
     * @return true if success.
     */
    public boolean add(User user) {
        User current = user;
        current.setCountry(getCountryID(user.getCountry()));
        current.setCity(getCityID(user.getCity()));
        boolean result = false;
        if (!store.findAll().contains(current)) {
            store.add(current);
            result = true;
        }
        return result;
    }

    /**
     * Check user and new parameters and update user.
     * @param user to check.
     * @param newname name to change.
     * @param newlogin login to change.
     * @param newemail email to change.
     * @return true if success.
     */
    public boolean update(User user, String newname, String newlogin, String newemail, String newpassword, String newrole, String newcountry, String newcity) {
        boolean result = false;
        User update = new User();
        if (store.findAll().contains(user)) {
            if (newname != null) {
                update.setName(newname);
            } else {
                update.setName(user.getName());
            }
            if (newlogin != null) {
                update.setLogin(newlogin);
            } else {
                update.setLogin(user.getLogin());
            }
            if (newemail != null) {
                update.setEmail(newemail);
            } else {
                update.setEmail(user.getEmail());
            }
            if (newpassword != null) {
                update.setPassword(newpassword);
            } else {
                update.setPassword(user.getPassword());
            }
            if (newrole != null) {
                update.setRole(newrole);
            } else {
                update.setRole(user.getRole());
            }
            if (newcountry != null) {
                update.setCountry(getCountryID(newcountry));
            } else {
                update.setCountry(getCountryID(user.getCountry()));
            }
            if (newcity != null) {
                update.setCity(getCityID(newcity));
            } else {
                update.setCity(getCityID(user.getCity()));
            }
            if (!store.findAll().contains(update)) {
                store.update(user, update);
                result = true;
            }
        }
        return result;
    }

    /**
     * Check if user exists and delete it.
     * @param user to check.
     * @return true if success.
     */
    public boolean delete(User user) {
        boolean result = false;
        if (store.findAll().contains(user)) {
            store.delete(user);
            result = true;
        }
        return result;
    }

    /**
     * Find all users.
     * @return User list.
     */
    public List<User> findAll() {
        List<User> result = store.findAll();
        for (User user : result) {
            user.setCountry(getCountryName(user.getCountry()));
            user.setCity(getCityName(user.getCity()));
        }
        return result;
    }

    /**
     * Find User by id..
     * @param id to find.
     * @return Optional of user.
     */
    public Optional<User> findById(String id) {
        return store.findById(id);
    }

    /**
     * Find User by id to display.
     * @param id to find.
     * @return Optional of user.
     */
    public Optional<User> findByIdView(String id) {
        User current = store.findById(id).get();
        current.setCountry(getCountryName(current.getCountry()));
        current.setCity(getCityName(current.getCity()));
        return Optional.of(current);
    }

    /**
     * Find User by login.
     * @param login to find.
     * @return Optional of user.
     */
    public Optional<User> findByLogin(String login) {
        Optional<User> result = store.findByLogin(login);
        return result;
    }

    /**
     * Get roles list.
     * @return list of all roles.
     */
    public List<String> findRoles() {
        List<String> result = new ArrayList<>();
        for (Role role : store.getRoles()) {
            result.add(role.getRole());
        }
        return result;
    }

    /**
     * Find user with same credentials.
     * @param login to check.
     * @param password to check.
     * @return optional of user.
     */
    public Optional<User> authenticate(String login, String password) {
        return store.checkCredentials(login, password);
    }

    /**
     * Get list of countries.
     * @return list of all countries.
     */
    public List<Country> getCountries() {
        return store.getCountries();
    }

    /**
     * Get list of cities.
     * @return list of all cities.
     */
    public List<City> getCities() {
        return store.getCities();
    }

    /**
     * Get list of cities in one country.
     * @param name id to find.
     * @return list of cities.
     */
    public List<City> getCitiesByCountryName(String name) {
        return store.getCitiesByCountryName(getCountryID(name));
    }

    /**
     * Get country ID by name.
     * @param name to find.
     * @return country ID.
     */
    public String getCountryID(String name) {
        return store.getCountryID(name);
    }

    /**
     * Get city ID by name.
     * @param name to find.
     * @return city ID.
     */
    public String getCityID(String name) {
        return store.getCityID(name);
    }

    /**
     * Get country name by ID.
     * @param id to find.
     * @return country name.
     */
    String getCountryName(String id) {
        return store.getCountryName(id);
    }

    /**
     * Get city name by ID.
     * @param id to find.
     * @return city name.
     */
    public String getCityName(String id) {
        return store.getCityName(id);
    }
}
