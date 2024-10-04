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

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private Long transactionIdCounter = 1L;  // For auto-generating transaction IDs

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(AccountDTO accountDTO) throws IllegalArgumentException {
        if (accountDTO.getInitialBalance() < 0){
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
        Account account = new Account(null, accountDTO.getName(), accountDTO.getInitialBalance());
        return accountRepository.save(account);
    }

    public Account findAccountById(Long accountId) throws Exception {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new Exception("Account with ID " + accountId + " not found.");
        }
        return account;
    }

    public void transferFunds(Long fromAccountId, TransactionDTO transactionDTO) throws Exception {
        Account fromAccount = findAccountById(fromAccountId);
        Account toAccount = findAccountById(transactionDTO.getToAccountId());

        if (fromAccount.getBalance() < transactionDTO.getAmount()) {
            throw new Exception("Insufficient funds.");
        }

        // Update balances
        fromAccount.updateBalance(-transactionDTO.getAmount());
        toAccount.updateBalance(transactionDTO.getAmount());

        // Create and add transaction
        Transaction transaction = new Transaction(transactionIdCounter++, fromAccountId, transactionDTO.getToAccountId(), transactionDTO.getAmount());
        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);

        // Update accounts in the repository
        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);
    }

    public List<Transaction> getTransactionHistory(Long accountId) throws Exception {
        Account account = findAccountById(accountId);
        return account.getTransactions();
    }

    public void deleteAccount(Long accountId) throws Exception {
        if (!accountRepository.existsById(accountId)) {
            throw new Exception("Account not found.");
        }
        accountRepository.delete(accountId);
    }

    public Map<Long, Account> getAllAccounts() {
        return accountRepository.findAll();
    }

}


