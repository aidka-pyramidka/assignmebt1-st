package com.example.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountTest {

    @Test
    void constructor_withValidBalance_initializesCorrectly() {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        assertEquals(100.0, account.getBalance(), 1e-9);
    }

    @Test
    void constructor_withNegativeBalance_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("A-001", "Alice", -1.0));
    }

    @Test
    void deposit_withValidAmount_increasesBalance() {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        account.deposit(50.0);
        assertEquals(150.0, account.getBalance(), 1e-9);
    }

    @Test
    void deposit_withZeroAmount_throwsException() {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(0.0));
    }

    @Test
    void deposit_withNegativeAmount_throwsException() {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-1.0));
    }

    @Test
    void withdraw_withValidAmount_reducesBalance() {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        account.withdraw(40.0);
        assertEquals(60.0, account.getBalance(), 1e-9);
    }

    @Test
    void withdraw_withAmountEqualToBalance_succeeds() {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        account.withdraw(100.0);
        assertEquals(0.0, account.getBalance(), 1e-9);
    }

    @Test
    void withdraw_exceedingBalance_throwsException() {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(100.01));
    }

    @Test
    void withdraw_withZeroAmount_throwsException() {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(0.0));
    }

    @Test
    void withdraw_withNegativeAmount_throwsException() {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-5.0));
    }
}
