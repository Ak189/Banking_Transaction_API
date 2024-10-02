package com.example.banking_transactions.service;

import com.example.banking_transactions.api.model.Account;
import com.example.banking_transactions.api.model.Transaction;
import com.example.banking_transactions.dto.AccountDTO;
import com.example.banking_transactions.dto.TransactionDTO;
import com.example.banking_transactions.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private Long transactionIdCounter = 1L;  // For auto-generating transaction IDs

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // 1. Create a new user account
    public Account createAccount(AccountDTO accountDTO) {
        Account account = new Account(null, accountDTO.getName(), accountDTO.getInitialBalance());
        return accountRepository.save(account);
    }

    // 2. Transfer funds between accounts
    public void transferFunds(Long fromAccountId, TransactionDTO transactionDTO) throws Exception {
        Account fromAccount = accountRepository.findById(fromAccountId);
        Account toAccount = accountRepository.findById(transactionDTO.getToAccountId());

        if (fromAccount == null) {
            throw new Exception("From account not found.");
        }

        if (toAccount == null) {
            throw new Exception("To account not found.");
        }

        if (fromAccount.getBalance() < transactionDTO.getAmount()) {
            throw new Exception("Insufficient funds.");
        }

        // Perform the transfer
        fromAccount.updateBalance(-transactionDTO.getAmount());
        toAccount.updateBalance(transactionDTO.getAmount());

        // Create a transaction and update both accounts' transaction history
        Transaction transaction = new Transaction(transactionIdCounter++, fromAccountId, transactionDTO.getToAccountId(), transactionDTO.getAmount());
        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);

        // Update both accounts in the repository
        accountRepository.update(fromAccount);
        accountRepository.update(toAccount);
    }

    // 3. Retrieve transaction history for a given account
    public List<Transaction> getTransactionHistory(Long accountId) {
        Account account = accountRepository.findById(accountId);
        if (account == null) {
            return null;  // Handle with a proper exception if needed
        }
        return account.getTransactions();
    }
}

