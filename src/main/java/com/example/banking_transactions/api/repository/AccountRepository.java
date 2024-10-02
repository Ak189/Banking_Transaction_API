package com.example.banking_transactions.api.repository;

import com.example.banking_transactions.api.model.Account;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository

public class AccountRepository {
    private Map<Long, Account> accountMap = new HashMap<>();
    private Long accountIdCounter = 1L;

    public Account save(Account account){
        account.setId(accountIdCounter++);
        accountMap.put(account.getId(), account);
        return account;
    }

    public Account findById(Long accountId){
        return accountMap.get(accountId);
    }

    public boolean existsById(Long accountId){
        return accountMap.containsKey(accountId);
    }

    public Map<Long, Account> findAll(){
        return accountMap;
    }

    public void update(Account account){
        accountMap.put(account.getId(), account);
    }

    public void delete(Long accountId){
        accountMap.remove(accountId);
    }
}
