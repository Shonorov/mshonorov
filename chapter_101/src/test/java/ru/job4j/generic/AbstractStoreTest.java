package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
/**
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class AbstractStoreTest {

    @Test
    public void whenAddUserOrRoleThenFind() {
        AbstractStore<Role> roles = new RoleStore(new SimpleArray(new Role[5]));
        AbstractStore<User> users = new UserStore(new SimpleArray(new User[5]));
        Role role1 = new Role("role1");
        User user1 = new User("user1");
        roles.add(role1);
        users.add(user1);
        assertThat(roles.findById("role1"), is(role1));
        assertThat(users.findById("user1"), is(user1));
    }

    @Test
    public void whenReplaceUserOrRoleAndDeleteThenFind() {
        AbstractStore<Role> roles = new RoleStore(new SimpleArray(new Role[5]));
        AbstractStore<User> users = new UserStore(new SimpleArray(new User[5]));
        Role role1 = new Role("role1");
        User user1 = new User("user1");
        roles.add(new Role("role0"));
        users.add(new User("user0"));
        roles.replace("role0", role1);
        users.replace("user0", user1);
        assertThat(roles.findById("role1"), is(role1));
        assertThat(users.findById("user1"), is(user1));
        assertThat(roles.delete("role1"), is(true));
        assertThat(users.delete("user1"), is(true));
        assertThat(roles.findById("role1"), is(nullValue()));
        assertThat(users.findById("user1"), is(nullValue()));
    }
}
