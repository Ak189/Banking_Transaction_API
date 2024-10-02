package com.example.banking_transactions.service;

import com.example.banking_transactions.api.controller.AccountController;
import com.example.banking_transactions.api.model.Account;
import com.example.banking_transactions.api.model.Transaction;
import com.example.banking_transactions.dto.AccountDTO;
import com.example.banking_transactions.dto.TransactionDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccountService {

    private Map<Long, Account> accountMap = new HashMap<>();
    private Long accountIdCounter = 1L;
    private Long transactionIdCounter = 1L;

    public Account createAccount(AccountDTO accountDTO){
        Account account = new Account(accountIdCounter++, accountDTO.getName(), accountDTO.getInitialBalance());
        accountMap.put(account.getId(), account);
        return account;
    }

    public void transferFunds(Long fromAccountId, TransactionDTO transactionDTO) throws Exception {
        Account fromAccount = accountMap.get(fromAccountId);
        Account toAccount = accountMap.get(transactionDTO.getToAccountId());

        if (fromAccount == null) {
            throw new Exception("Sender's account not found!");
        }

        if (toAccount == null) {
            throw new Exception("Receiver's account not found!");
        }

        if (fromAccount.getBalance() < transactionDTO.getAmount()){
            throw new Exception("Insufficient funds!");
        }

        fromAccount.updateBalance(-transactionDTO.getAmount());
        toAccount.updateBalance(transactionDTO.getAmount());

        Transaction transaction = new Transaction(transactionIdCounter++, fromAccountId, transactionDTO.getToAccountId(), transactionDTO.getAmount());
        fromAccount.addTransaction(transaction);
        toAccount.addTransaction(transaction);
    }

    public List<Transaction> getTransactionHistory(Long accountId) {
        Account account = accountMap.get(accountId);

        if (account == null){
            return null;
        }

        return account.getTransactions();
    }

    public Account getAccount(Long accountID){
        return accountMap.get(accountID);
    }
}
