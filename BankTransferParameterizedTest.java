package com.example.bank;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BankTransferParameterizedTest {

    @ParameterizedTest
    @CsvSource({
            "500.0,100.0,true",
            "500.0,500.0,true",
            "500.0,500.01,false",
            "500.0,-50.0,false",
            "500.0,0.0,false"
    })
    void transfer_parameterizedScenarios(double fromBalance, double transferAmount, boolean shouldSucceed) {
        Bank bank = new Bank();
        BankAccount from = new BankAccount("A-001", "Alice", fromBalance);
        BankAccount to = new BankAccount("A-002", "Bob", 200.0);
        bank.addAccount(from);
        bank.addAccount(to);

        double initialFromBalance = from.getBalance();
        double initialToBalance = to.getBalance();

        if (shouldSucceed) {
            bank.transfer(from, to, transferAmount);
            assertEquals(initialFromBalance - transferAmount, from.getBalance(), 1e-9);
            assertEquals(initialToBalance + transferAmount, to.getBalance(), 1e-9);
        } else {
            assertThrows(IllegalArgumentException.class, () -> bank.transfer(from, to, transferAmount));
            assertEquals(initialFromBalance, from.getBalance(), 1e-9);
            assertEquals(initialToBalance, to.getBalance(), 1e-9);
        }
    }
}
