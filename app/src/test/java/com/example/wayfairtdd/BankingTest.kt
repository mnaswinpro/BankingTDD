package com.example.wayfairtdd

import org.junit.Assert.assertEquals
import org.junit.Test

class BankingTest {

    val testTimeUtil: TimeUtil = TestTimeUtil()

    @Test
    fun `deposit money`() {
        val money = Money(amount = 10)
        val account = Account(
            "123",
            0L,
        )
        assertEquals(account.balance, 0)

        account.depositMoney(money)

        assertEquals(account.balance, 10)

    }

    @Test
    fun `print account balance`() {
        val account = Account("123", 0L)
        val accountBalancePrinter = AccountBalancePrinter(testTimeUtil)

        val result = accountBalancePrinter.printBalance(account)

        assertEquals(result, "Date: 27/8/24 Time: 11:30 am, Balance: 0")
    }

    class TestTimeUtil :TimeUtil{
        override fun currentDateTimeString(): String {
            return "Date: 27/8/24 Time: 11:30 am"
        }

    }

    @Test
    fun `print account activity`() {
        val money = Money(amount = 10)
        val account = Account("123", 0L)
        val accountStatementPrinter = AccountStatementPrinter()

        assertEquals(account.statements.size, 0)

        account.depositMoney(money)

        assertEquals(account.statements.size, 1)
        assertEquals(account.statements[0].type, TransactionType.DEPOSIT)

        val result = accountStatementPrinter.printStatement(account)

        assertEquals(result, "Amount: 10, Type: DEPOSIT")
    }

}