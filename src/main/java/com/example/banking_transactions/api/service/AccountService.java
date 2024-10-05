package com.example.banking_transactions.api.service;

import com.example.banking_transactions.api.model.Account;
import com.example.banking_transactions.api.model.Transaction;
import com.example.banking_transactions.api.dto.AccountDTO;
import com.example.banking_transactions.api.dto.TransactionDTO;
import com.example.banking_transactions.api.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * The AccountService class handles the logic for managing @link account objects and performing operations
 * such as creating accounts, transferring funds, retrieving transactions, deleting account and retrieving
 * account by id.
 */
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private Long transactionIdCounter = 1L;  // For auto-generating transaction IDs
    /**
     * Constructor class to initialize AccountService with the AccountRepository dependency.
     * @param accountRepository The repository that handles account data persistence.
     */
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Creates a new account based on the provided, @link AccountDTO object.
     *
     * @param accountDTO Data Transfer Object containing account details i.e, name, initial balance.
     * @return A new Account.
     * @throws IllegalArgumentException If the initial balance is negative.
     */
    public Account createAccount(AccountDTO accountDTO) {
        // Check if the SIN number or driving license already exists
        if (accountRepository.existsBySinNumberOrDrivingLicense(accountDTO.getSinNumber(), accountDTO.getDrivingLicense())) {
            throw new IllegalArgumentException("Account with this SIN number or driving license already exists.");
        }

        Account account = new Account(null, accountDTO.getFirstName(), accountDTO.getMiddleName(), accountDTO.getLastName(),
                accountDTO.getAddressLine1(), accountDTO.getAddressLine2(), accountDTO.getCity(), accountDTO.getState(),
                accountDTO.getPostalCode(), accountDTO.getDrivingLicense(), accountDTO.getSinNumber(),
                accountDTO.getInitialBalance());

        return accountRepository.save(account);
    }

    /**
     * Finds account by an id.
     *
     * @param accountId The id of the account to be retrieved.
     * @return The account object if found.
     * @throws Exception If the account with the specified ID does not exist.
     */
    public Account findAccountById(Long accountId) throws Exception {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new Exception("Account with ID " + accountId + " not found.");
        }
        return account;
    }

    /**
     * Transfers funds from one account to another.
     *
     * @param fromAccountId The id of the account transferring the funds.
     * @param transactionDTO Data Transfer Object containing the recipient account id and the transfer amount.
     * @throws Exception If the sender has insufficient funds or if any of the accounts involved do not exist.
     */

    public void transferFunds(Long fromAccountId, TransactionDTO transactionDTO) throws Exception {
        Account fromAccount = findAccountById(fromAccountId);
        Account toAccount = findAccountById(transactionDTO.getToAccountId());

        if (fromAccount.getBalance() < transactionDTO.getAmount()) {
            throw new Exception("Insufficient funds.");
        }

        // Updates balance
        fromAccount.updateBalance(-transactionDTO.getAmount());
        toAccount.updateBalance(transactionDTO.getAmount());

        // Create and add to transaction history
        Transaction transaction = new Transaction(transactionIdCounter++, fromAccountId, transactionDTO.getToAccountId(), transactionDTO.getAmount());
        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);

        // Update accounts in the repository
        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);
    }

    /**
     * Retrieves the transaction history of a specified account.
     *
     * @param accountId The id of the account whose transaction history is requested.
     * @return list of transactions associated with the account.
     * @throws Exception If the account does not exist.
     */

    public List<Transaction> getTransactionHistory(Long accountId) throws Exception {
        Account account = findAccountById(accountId);
        return account.getTransactions();
    }

    /**
     * Deletes an account by its id.
     *
     * @param accountId The id of the account to be deleted.
     * @throws Exception If the account does not exist.
     */

    public void deleteAccount(Long accountId) throws Exception {
        if (!accountRepository.existsById(accountId)) {
            throw new Exception("Account not found.");
        }
        accountRepository.delete(accountId);
    }

    /**
     * Retrieves all accounts stored in the repository.
     *
     * @return A map of accounts where the key is the account ID and the value is the account object.
     */

    public Map<Long, Account> getAllAccounts() {
        return accountRepository.findAll();
    }

}


