package com.example.bank;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        BankAccountTest.class,
        BankTest.class,
        BankAccountParameterizedTest.class,
        BankTransferParameterizedTest.class
})
class BankSystemTestSuite {
}
