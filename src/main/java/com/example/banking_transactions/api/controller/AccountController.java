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
import jakarta.validation.Valid;


import java.util.List;
//Spring REST controller, enables the class to handle HTTP requests.
@RestController
//API's defined in the class will have a common base URL /accounts.
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    //Constructor injection to inject AccountService.
    @Autowired

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    /**
     * Create a new account.
     *
     * @param accountDTO A DTO object containing the following details; name, initial balance.
     * @return ResponseEntity with the created Account(name, balancce and id) and HTTP status CREATED.
     */
    @PostMapping
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountDTO accountDTO) {
        try {
            Account account = accountService.createAccount(accountDTO);
            return new ResponseEntity<>(account, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Log the error message (optional)
            System.out.println("Error creating account: " + e.getMessage());
            // Return BAD_REQUEST with the error message
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            // Catch any other unexpected exceptions
            System.out.println("Unexpected error: " + e.getMessage());
            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * GET account details by account ID.
     *
     * @param id Account ID to search for.
     * @return ResponseEntity with the found Account including details name balance transaction history and HTTP status OK if found,
     * or NOT_FOUND if the account does not exist.
     */
    @GetMapping("/accounts/{id}")
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

    /**
     * Transfer funds from one account to another.
     *
     * @param id ID of the source account.
     * @param transactionDTO A DTO object containing the following details about the transaction; target account ID and amount.
     * @return ResponseEntity with a success message and HTTP status OK if transfer is successful,
     * or BAD_REQUEST if there is an error (e.g., insufficient funds).
     */
    @PostMapping("/{id}/transfer")
    public ResponseEntity<String> transferFunds(@PathVariable Long id, @Valid @RequestBody TransactionDTO transactionDTO){
        try {
            accountService.transferFunds(id, transactionDTO);
            return new ResponseEntity<>("Transfer successful", HttpStatus.OK);
        }   catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    /**
     * GET the transaction history for a specific account by ID.
     *
     * @param id The account ID for which to retrieve transactions.
     * @return ResponseEntity with the list of transactions for the account and HTTP status OK if found,
     *         or NOT_FOUND if there are no transactions for that account.
     */
    @GetMapping("/{id}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistory(@PathVariable Long id) throws Exception {
        List <Transaction> transactions = accountService.getTransactionHistory(id);
        if (transactions != null){
            return new ResponseEntity<>(transactions, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Delete an account by ID.
     *
     * @param id The ID of the account to be deleted.
     * @return ResponseEntity with HTTP status NO_CONTENT if successful,
     *         or NOT_FOUND if the account does not exist.
     */
    @DeleteMapping("/accounts/{id}")
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


}
