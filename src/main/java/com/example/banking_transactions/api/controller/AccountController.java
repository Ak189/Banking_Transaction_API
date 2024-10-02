package com.example.banking_transactions.api.controller;

import com.example.banking_transactions.dto.TransactionDTO;
import com.example.banking_transactions.dto.AccountDTO;
import com.example.banking_transactions.api.model.Transaction;
import com.example.banking_transactions.api.model.Account;
import com.example.banking_transactions.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountDTO accountDTO){
        Account account = accountService.createAccount(accountDTO);
        return new ResponseEntity<>(account, HttpStatus.CREATED);
    }
    @PostMapping("/{id/transfer")
    public ResponseEntity<String> transferFunds(@PathVariable Long id, @RequestBody TransactionDTO transactionDTO){
        try {
            accountService.transferFunds(id, transactionDTO);
            return new ResponseEntity<>("Transfer successful", HttpStatus.OK);
        }   catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable Long id) {
        List <Transaction> transactions = accountService.getTransactionHistory(id);
        if (transactions != null){
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
