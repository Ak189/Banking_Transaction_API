package com.example.banking_transactions.api.controller;

import com.example.banking_transactions.api.dto.TransactionDTO;
import com.example.banking_transactions.api.dto.AccountDTO;
import com.example.banking_transactions.api.model.Transaction;
import com.example.banking_transactions.api.model.Account;
import com.example.banking_transactions.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    @Autowired

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO){
        Account account = accountService.createAccount(accountDTO);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        try {
            System.out.println("Received request for account ID: " + id);
            Account account = accountService.findAccountById(id);
            if (account != null) {
                System.out.println("Account found: " + account);
                return new ResponseEntity<>(account, HttpStatus.OK);
            } else {
                System.out.println("Account not found for ID: " + id);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Error retrieving account: " + e.getMessage());
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/{id}/transfer")
    public ResponseEntity<String> transferFunds(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO){
        try {
            accountService.transferFunds(id, transactionDTO);
            return new ResponseEntity<>("Transfer successful", HttpStatus.OK);
        }   catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable Long id) throws Exception {
        List <Transaction> transactions = accountService.getTransactionHistory(id);
        if (transactions != null){
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        System.out.println("Attempting to delete account with ID: " + id);
        try {
            accountService.deleteAccount(id);
            System.out.println("Account deleted successfully.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            System.out.println("Error deleting account: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping
    public ResponseEntity<Map<Long, Account>> getAllAccounts() {
        Map<Long, Account> accounts = accountService.getAllAccounts();
        if (accounts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}
