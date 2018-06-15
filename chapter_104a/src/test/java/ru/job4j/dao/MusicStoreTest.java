package ru.job4j.dao;
import org.junit.Test;
import ru.job4j.model.Style;
import ru.job4j.model.User;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class MusicStoreTest {

    @Test
    public void whenAuthenticateAdminThenReturnAdmin() {
        MusicStore store = new MusicStore("music.properties");
        Optional<User> user = store.checkCredentials("admin", "admin");
        assertThat(user.get().getId(), is("0"));
    }

    @Test
    public void whenGetAllStylesThenSixCount() {
        MusicStore store = new MusicStore("music.properties");
        List<Style> styles = store.getAllStyles();
        assertThat(styles.size(), is(6));
    }

}

