package com.example.bank;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankAccountParameterizedTest {

    @ParameterizedTest
    @CsvSource({
            "50.0,150.0",
            "100.0,200.0",
            "250.75,350.75",
            "0.01,100.01"
    })
    void deposit_variousValidValues_updatesBalance(double amount, double expectedBalance) {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        account.deposit(amount);
        assertEquals(expectedBalance, account.getBalance(), 1e-9);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, -100.5})
    void deposit_invalidValues_throwException(double amount) {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(amount));
    }

    @ParameterizedTest
    @CsvSource({
            "50.0,50.0",
            "100.0,0.0",
            "99.99,0.01",
            "0.01,99.99"
    })
    void withdraw_variousValidValues_updatesBalance(double amount, double expectedBalance) {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        account.withdraw(amount);
        assertEquals(expectedBalance, account.getBalance(), 1e-9);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0, -1.0, -100.5})
    void withdraw_invalidValues_throwException(double amount) {
        BankAccount account = new BankAccount("A-001", "Alice", 100.0);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(amount));
    }
}
