package com.example.wayfairtdd

interface TimeUtil {
    fun currentDateTimeString(): String
}



class Account(
    val accountNumber: String,
    var balance: Long = 0L,
    ) {

    val statements = mutableListOf<Transaction>()

    fun depositMoney(money:Money) {
        if(money.amount < 0) {
            throw IllegalArgumentException("must be positive amount")
        }

        balance += money.amount
        statements.add(Transaction(money, TransactionType.DEPOSIT))
    }



}

class AccountBalancePrinter(
    private val timeUtil: TimeUtil,
) {
    fun printBalance(account: Account): String {
        return "${timeUtil.currentDateTimeString()}, Balance: ${account.balance}"
    }
}

class AccountStatementPrinter() {

    fun printStatement(account: Account): String {
//        val builder = StringBuilder("")
//        account.statements.forEach { transaction ->
//            builder.append("Amount: ${transaction.amount.amount}, Type: ${transaction.type}")
//        }
        return account.statements.joinToString("/n") {
            "Amount: ${it.amount.amount}, Type: ${it.type}"
        }

//        return builder.toString()
    }
}

//fun getDateTimeString() : String {
//    val current = System.currentTimeMillis()
//}


data class Transaction(
    val amount: Money,
    val type: TransactionType
)

enum class TransactionType {
    WITHDRAW,
    DEPOSIT
}