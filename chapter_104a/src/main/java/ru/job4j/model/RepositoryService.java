package ru.job4j.model;

import ru.job4j.dao.MusicStore;
import ru.job4j.dao.Store;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Logic layout for web application.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class RepositoryService {
    /**
     * ValidateService singleton instance.
     * MemoryStore instance.
     */
    private static final RepositoryService INSTANCE = new RepositoryService();
    private final Store store = MusicStore.getInstance();

    public static RepositoryService getInstance() {
        return INSTANCE;
    }

    public Optional<User> authenticate(String login, String password) {
        return store.checkCredentials(login, password);
    }

    /**
     * Create new user.
     * @param user user to create.
     * @return true if success.
     */
    public boolean createUser(User user) {
        boolean result = false;
        if (!store.getUsers().contains(user)) {
            store.createUser(user);
            result = true;
        }
        return result;
    }

    /**
     * Update user fields and related items.
     * @param user user to update.
     * @param isStyleChanged true if user style changed.
     * @return true if success.
     */
    public boolean updateUser(User user, boolean isStyleChanged) {
        boolean result = false;
        if (!getAllUsers().contains(user) || isStyleChanged) {
            Optional<User> current = store.findUserByID(user.getId());
            Address address = store.findAddressByID(current.get().getAddress()).get();
            address.setAddress(user.getAddress());
            user.setAddress(address.getId());
            store.updateAddress(address);
            store.updateUser(user);
            result = true;
        }
        return result;
    }

    /**
     * Delete not used and create new style entries.
     * @param user related user.
     * @param styles new styles.
     */
    public void updateUserStyleEntries(User user, List<Style> styles) {
        List<Style> create = styles;
        for (StyleEntry styleEntry : findStyleEntriesByUserID(user.getId())) {
            boolean delete = true;
            for (Style style : styles) {
                if (styleEntry.getStyleID().equals(style.getId())) {
                    create.remove(style);
                    delete = false;
                    break;
                }
            }
            if (delete) {
                store.deleteStyleEntry(styleEntry);
            }
        }
        for (Style style : create) {
            store.createStyleEntry(new StyleEntry(user.getId(), style.getId()));
        }
    }

    /**
     * Find user by ID.
     * @param id id to find.
     * @return optional of user.
     */
    public Optional<User> findUserByID(String id) {
        Optional<User> current = store.findUserByID(id);
        current.ifPresent(user -> user.setAddress(store.findAddressByID(user.getAddress()).get().getAddress()));
        return current;
    }

    /**
     * Remove user and all related entities.
     * @param user user to remove.
     * @return true if success.
     */
    public boolean deleteUser(User user) {
        boolean result = false;
        if (store.getUsers().contains(user)) {
            Optional<User> currentUser = store.findUserByID(user.getId());
            if (currentUser.isPresent()) {
                Optional<Address> currentAddress = store.findAddressByID(currentUser.get().getAddress());
                if (currentAddress.isPresent()) {
                    store.deleteAddress(currentAddress.get());
                    for (StyleEntry styleEntry : findStyleEntriesByUserID(user.getId())) {
                        store.deleteStyleEntry(styleEntry);
                    }
                    store.deleteUser(user);
                    result = true;
                }
            }
        }
        return result;
    }

    /**
     * Add address entry by name and return its id.
     * @param address address name.
     * @return new address's id.
     */
    public String createAddress(String address) {
        Address current = new Address(address);
        store.createAddress(current);
        return current.getId();
    }

    /**
     * Find all users.
     * @return list of users.
     */
    public List<User> getAllUsers() {
        List<User> users = store.getUsers();
        for (User user : users) {
            user.setStyles(getUserStylesString(user));
            Optional<Address> address = store.findAddressByID(user.getAddress());
            if (address.isPresent()) {
                user.setAddress(address.get().getAddress());
            }
        }
        return users;
    }

    /**
     * Find users by filter word in name, login, address, style, role fields.
     * @param search filter word.
     * @return list of filtered users.
     */
    public List<User> findUsersByFilter(String search) {
        List<User> result = new LinkedList<>();
        for (User user : getAllUsers()) {
            if (user.getName().toLowerCase().contains(search.toLowerCase())
                    || user.getLogin().toLowerCase().contains(search.toLowerCase())
                    || user.getAddress().toLowerCase().contains(search.toLowerCase())
                    || user.getStyles().toLowerCase().contains(search.toLowerCase())
                    || user.getRole().toLowerCase().contains(search.toLowerCase())) {
                result.add(user);
            }
        }
        return result;
    }

    /**
     * Fint all styles.
     * @return list of stiles.
     */
    public List<Style> getAllStyles() {
        return store.getAllStyles();
    }

    /**
     * Get user's styles as string.
     * @param user user to find.
     * @return address list string.
     */
    private String getUserStylesString(User user) {
        String result = "";
        for (Style style : store.getUserStyles(user)) {
            result = result + style.getStyle() + ",";
        }
        return result.length() != 0 ? result.substring(0, result.length() - 1) : "";
    }

    /**
     * Get current user's styles.
     * @param user user to find.
     * @return list of styles.
     */
    public List<Style> getUserStyles(User user) {
        return store.getUserStyles(user);
    }

    /**
     * Get all style entries.
     * @return list of music style entries.
     */
    private List<StyleEntry> getStyleEntries() {
        return store.getStyleEntries();
    }

    /**
     * Create new music style entry.
     * @param entry entry to create.
     */
    public void createStyleEntry(StyleEntry entry) {
        if (!getStyleEntries().contains(entry)) {
            store.createStyleEntry(entry);
        }
    }

    /**
     * Find music style entries of user by ID.
     * @param id id to find.
     * @return list of entries.
     */
    private List<StyleEntry> findStyleEntriesByUserID(String id) {
        List<StyleEntry> result = new LinkedList<>();
        for (StyleEntry styleEntry : getStyleEntries()) {
            if (styleEntry.getUserID().equals(id)) {
                result.add(styleEntry);
            }
        }
        return result;
    }

    /**
     * Get all existing roles.
     * @return list of roles.
     */
    public List<Role> getAllRoles() {
        return store.getAllRoles();
    }

}
