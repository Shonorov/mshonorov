package ru.job4j.bank;
/**
 * Tests for bank operations.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

public class BankTest {

    @Test
    public void whenAddUserAndAccountThenReturnAccounts() {
        Bank bank = new Bank();
        User user = new User("Ivan", "001");
        Account account = new Account(100D, "100");
        bank.addUser(user);
        bank.addAccountToUser("001", account);
        ArrayList<Account> expect = new ArrayList<>(Arrays.asList(account));
        assertThat(bank.getUserAccounts("001"), is(expect));
    }

    @Test
    public void whenDeleteUserThenNoAccounts() {
        Bank bank = new Bank();
        User user = new User("Ivan", "001");
        bank.addUser(user);
        bank.deleteUser(user);
        assertThat(bank.getUserAccounts("001").size(), is(0));
    }

    @Test
    public void whenDeleteAccountThenEmptyArray() {
        Bank bank = new Bank();
        User user = new User("Ivan", "001");
        bank.addUser(user);
        Account account = new Account(100D, "100");
        bank.deleteAccountFromUser("001", account);
        assertThat(bank.getUserAccounts("001").size(), is(0));
    }

    @Test
    public void whenTransferMoneyThenTrue() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "001");
        User user2 = new User("Sergey", "002");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account(300D, "100");
        Account account2 = new Account(500D, "200");
        bank.addAccountToUser("001", account1);
        bank.addAccountToUser("002", account2);
        assertThat(bank.transferMoney("001", "100", "002", "200", 150), is(true));
    }

    @Test
    public void whenTransferMoneyNotEnoughThenFalse() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "001");
        User user2 = new User("Sergey", "002");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account(100D, "100");
        Account account2 = new Account(500D, "200");
        bank.addAccountToUser("001", account1);
        bank.addAccountToUser("002", account2);
        assertThat(bank.transferMoney("001", "100", "002", "200", 150), is(false));
    }

    @Test
    public void whenTransferMoneyWrongAccountThenFalse() {
        Bank bank = new Bank();
        User user1 = new User("Ivan", "001");
        User user2 = new User("Sergey", "002");
        bank.addUser(user1);
        bank.addUser(user2);
        Account account1 = new Account(300D, "100");
        Account account2 = new Account(500D, "200");
        bank.addAccountToUser("001", account1);
        bank.addAccountToUser("002", account2);
        assertThat(bank.transferMoney("001", "100", "002", "300", 150), is(false));
    }
}
