package com.example.bank;

public class BankAccount {
    private final String accountId;
    private final String ownerName;
    private double balance;

    public BankAccount(String accountId, String ownerName, double balance) {
        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("Account ID must not be blank.");
        }
        if (ownerName == null || ownerName.isBlank()) {
            throw new IllegalArgumentException("Owner name must not be blank.");
        }
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance must not be negative.");
        }
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        balance -= amount;
    }
}
