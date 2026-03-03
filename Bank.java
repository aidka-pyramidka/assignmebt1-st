package com.example.bank;

import java.util.HashMap;
import java.util.Map;

public class Bank {
    private final Map<String, BankAccount> accounts = new HashMap<>();

    public void addAccount(BankAccount account) {
        if (account == null) {
            throw new IllegalArgumentException("Account must not be null.");
        }
        if (accounts.containsKey(account.getAccountId())) {
            throw new IllegalArgumentException("Account ID already exists in this bank.");
        }
        accounts.put(account.getAccountId(), account);
    }

    public double getTotalAssets() {
        return accounts.values().stream()
                .mapToDouble(BankAccount::getBalance)
                .sum();
    }

    public void transfer(BankAccount from, BankAccount to, double amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Source and destination accounts must not be null.");
        }
        if (from.getAccountId().equals(to.getAccountId())) {
            throw new IllegalArgumentException("Source and destination must be different accounts.");
        }
        if (!accounts.containsKey(from.getAccountId()) || !accounts.containsKey(to.getAccountId())) {
            throw new IllegalArgumentException("Both accounts must be registered in this bank.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }
        if (amount > from.getBalance()) {
            throw new IllegalArgumentException("Insufficient funds for transfer.");
        }

        from.withdraw(amount);
        to.deposit(amount);
    }
}
