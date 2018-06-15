package ru.job4j.dao;

import ru.job4j.model.*;

import java.util.List;
import java.util.Optional;

/**
 * DAO layout interface for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public interface Store {
    /**
     * Create new user.
     * @param user user to create.
     */
    void createUser(User user);

    /**
     * Update user.
     * @param user user to update.
     */
    void updateUser(User user);

    /**
     * Delete user.
     * @param user user to delete.
     */
    void deleteUser(User user);

    /**
     * Find user by id.
     * @param id to find.
     * @return optional of User.
     */
    Optional<User> findUserByID(String id);

    /**
     * Find all users.
     * @return all users list.
     */
    List<User> getUsers();

    /**
     * Create user address.
     * @param address address to create.
     */
    void createAddress(Address address);

    /**
     * Change user address.
     * @param address address tu update.
     */
    void updateAddress(Address address);

    /**
     * Delete address.
     * @param address address to delete.
     */
    void deleteAddress(Address address);

    /**
     * Retrieva all addreses from table.
     * @return list of addresses.
     */
    List<Address> getAllAddresses();

    /**
     * Find address by ID.
     * @param id id to find.
     * @return Optional of Address.
     */
    Optional<Address> findAddressByID(String id);

    /**
     * Authenticate user.
     * @param login user login.
     * @param password login password.
     * @return Optional of user.
     */
    Optional<User> checkCredentials(String login, String password);

    /**
     * Get all style entries list.
     * @return
     */
    List<StyleEntry> getStyleEntries();

    /**
     * Add StyleEntry to the database.
     * @param entry entry to add.
     */
    void createStyleEntry(StyleEntry entry);

    /**
     * Remove StyleEntry from database.
     * @param entry StyleEntry to remove.
     */
    void deleteStyleEntry(StyleEntry entry);

    /**
     * Update user style entry.
     * @param entry entry to update.
     */
    void updateStyleEntry(StyleEntry entry);

    /**
     * Get user's music styles.
     * @param user user to find.
     * @return list of styles.
     */
    List<Style> getUserStyles(User user);

    /**
     * Retrieve all styles from table.
     * @return list of styles.
     */
    List<Style> getAllStyles();

    /**
     * Get style by ID from table.
     * @param id id to find.
     * @return Style.
     */
    Optional<Style> findStyleByID(String id);

    /**
     * Retrieve all roles from table.
     * @return
     */
    List<Role> getAllRoles();
}
