package com.example.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankTest {

    @Test
    void addAccount_accountsAreManagedByBank() {
        Bank bank = new Bank();
        bank.addAccount(new BankAccount("A-001", "Alice", 100.0));
        bank.addAccount(new BankAccount("A-002", "Bob", 200.0));

        assertEquals(300.0, bank.getTotalAssets(), 1e-9);
    }

    @Test
    void getTotalAssets_returnsSumOfAllBalances() {
        Bank bank = new Bank();
        bank.addAccount(new BankAccount("A-001", "Alice", 100.5));
        bank.addAccount(new BankAccount("A-002", "Bob", 299.5));
        bank.addAccount(new BankAccount("A-003", "Chris", 600.0));

        assertEquals(1000.0, bank.getTotalAssets(), 1e-9);
    }

    @Test
    void transfer_validAmount_updatesBothAccounts() {
        Bank bank = new Bank();
        BankAccount from = new BankAccount("A-001", "Alice", 500.0);
        BankAccount to = new BankAccount("A-002", "Bob", 100.0);
        bank.addAccount(from);
        bank.addAccount(to);

        bank.transfer(from, to, 200.0);

        assertEquals(300.0, from.getBalance(), 1e-9);
        assertEquals(300.0, to.getBalance(), 1e-9);
    }

    @Test
    void transfer_insufficientBalance_throwsAndKeepsBalancesUnchanged() {
        Bank bank = new Bank();
        BankAccount from = new BankAccount("A-001", "Alice", 150.0);
        BankAccount to = new BankAccount("A-002", "Bob", 75.0);
        bank.addAccount(from);
        bank.addAccount(to);

        assertThrows(IllegalArgumentException.class, () -> bank.transfer(from, to, 150.01));

        assertEquals(150.0, from.getBalance(), 1e-9);
        assertEquals(75.0, to.getBalance(), 1e-9);
    }
}
