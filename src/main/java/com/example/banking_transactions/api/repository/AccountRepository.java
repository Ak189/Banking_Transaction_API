package com.example.banking_transactions.api.repository;

import com.example.banking_transactions.api.model.Account;
import org.springframework.stereotype.Repository;
import java.util.concurrent.atomic.AtomicLong;

import java.util.HashMap;
import java.util.Map;

@Repository

public class AccountRepository {
    // Map that store account information with the account ID as the key.
    private Map<Long, Account> accountMap = new HashMap<>();
    //Generates unique id for the accounts.
    //private Long accountIdCounter = 1L;
    private AtomicLong accountIdCounter = new AtomicLong(1L);

    /**
     * Saves a new account to repository and assigns a new id.
     *
     * @param account The account object to be saved.
     * @return The saved account with assigned id.
     */

    public Account save(Account account) {
        if (existsBySinNumberOrDrivingLicense(account.getSinNumber(), account.getDrivingLicense())) {
            // Duplicate found, do not save
            return null;
        }
        account.setId(accountIdCounter.getAndIncrement());
        accountMap.put(account.getId(), account);
        return account;
    }

    /**
     * Finds an account by its id.
     *
     * @param accountId The id of the account to be retrieved.
     * @return The account object if found, or null if no account with the specified id exists.
     */

    public Account findById(Long accountId){
        return accountMap.get(accountId);
    }

    /**
     * Checks whether an account with a given id exists in the repository.
     *
     * @param accountId The id of the account to check.
     * @return True if the account exists, otherwise false.
     */

    public boolean existsById(Long accountId){
        return accountMap.containsKey(accountId);
    }

    /**
     * Retrieve all accounts from the repository.
     *
     * @return A map containing all accounts, keyed by their account IDs.
     */

    public Map<Long, Account> findAll(){
        return accountMap;
    }

    /**
     * Updates the existing account in the repository.
     * If the account does not exist,it will overwrite the data of the provided account.
     *
     * @param account The account object containing updated information.
     */

    public void update(Account account){
        accountMap.put(account.getId(), account);
    }

    /**
     * Deletes an account from the repository by its id.
     *
     * @param accountId The id of the account to be deleted.
     */

    public void delete(Long accountId){
        accountMap.remove(accountId);
    }

    public Account findBySinNumber(String sinNumber) {
        return accountMap.values().stream()
                .filter(account -> account.getSinNumber().equals(sinNumber))
                .findFirst()
                .orElse(null);
    }

    /**
     * Finds an account by its driving license.
     *
     * @param drivingLicense The driving license of the account to be retrieved.
     * @return The account object if found, or null if no account with the specified driving license exists.
     */
    public Account findByDrivingLicense(String drivingLicense) {
        return accountMap.values().stream()
                .filter(account -> account.getDrivingLicense().equals(drivingLicense))
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks whether an account with a given SIN number or driving license exists in the repository.
     *
     * @param sinNumber      The SIN number of the account to check.
     * @param drivingLicense The driving license of the account to check.
     * @return True if the account exists, otherwise false.
     */
    public boolean existsBySinNumberOrDrivingLicense(String sinNumber, String drivingLicense) {
        return accountMap.values().stream()
                .anyMatch(account -> account.getSinNumber().equals(sinNumber) ||
                        account.getDrivingLicense().equals(drivingLicense));
    }

}
