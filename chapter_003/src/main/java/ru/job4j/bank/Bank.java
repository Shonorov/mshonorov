package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Bank accounts management class.
 * @author MShonorov (shonorov@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class Bank {
    /**
     * All bank accounts.
     */
    private final Map<User, List<Account>> accounts = new HashMap<>();
    /**
     * Add user with empty account list.
     * @param user
     */
    public void addUser(User user) {
        this.accounts.putIfAbsent(user, new ArrayList<>());
    }
    /**
     * Delete user from accounts list.
     * @param user
     */
    public void deleteUser(User user) {
        this.accounts.remove(user);
    }
    /**
     * Add new account to existing user.
     * @param passport user's passport.
     * @param account new account.
     */
    public void addAccountToUser(String passport, Account account) {
        for (HashMap.Entry<User, List<Account>> entry : accounts.entrySet()) {
            if (entry.getKey().getPassport().equals(passport)) {
                entry.getValue().add(account);
            }
        }
    }
    /**
     * Remove account from user's account list.
     * @param passport user's passport.
     * @param account account to delete.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        for (HashMap.Entry<User, List<Account>> entry : accounts.entrySet()) {
            if (entry.getKey().getPassport().equals(passport)) {
                entry.getValue().remove(account);
            }
        }
    }
    /**
     * Get all user's accounts list.
     * @param passport user's passport.
     * @return accounts list.
     */
    public List<Account> getUserAccounts(String passport) {
        List<Account> result = null;
        for (HashMap.Entry<User, List<Account>> entry : accounts.entrySet()) {
            if (entry.getKey().getPassport().equals(passport)) {
                result = entry.getValue();
            }
        }
        return result;
    }
    /**
     * Transfer money.
     * Fails if wrong passports, requisites or money amount.
     * @param srcPassport source user passport.
     * @param srcRequisite target user requisites.
     * @param destPassport source user passport.
     * @param dstRequisite target user requisites.
     * @param amount amount of money to transfer.
     * @return success or not.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        boolean result = false;
        Account source = null;
        Account target = null;
        for (HashMap.Entry<User, List<Account>> entry : accounts.entrySet()) {
            if (entry.getKey().getPassport().equals(srcPassport) || entry.getKey().getPassport().equals(destPassport)) {
                for (Account account : entry.getValue()) {
                    source = account.getRequisites().equals(srcRequisite) ? account : source;
                    target = account.getRequisites().equals(dstRequisite) ? account : target;
                }
            }
        }
        if (source != null && target != null && source.getValue() >= amount) {
            source.addValue(-amount);
            target.addValue(amount);
            result = true;
        }
        return result;
    }
}
