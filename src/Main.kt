import java.util.Scanner

// FUNCTION THAT HANDLES THE WITHDRAWALS FOR GENERIC AND CREDIT BANK ACCOUNTS
fun withdraw(amount: Double, accountBalance: DoubleArray): Double {
    accountBalance[0] -= amount
    println("You successfully withdrew R$amount. The current balance is R${accountBalance[0]} .")
    return amount
}

// FUNCTION THAT HANDLES THE WITHDRAWAL FOR CREDIT ACCOUNT
fun creditWithdraw(amount: Double, accountBalance: DoubleArray): Double {
    if (amount > accountBalance[0]) {
        println("Not enough money on this account! The current balance is R${accountBalance[0]} .")
        return 0.0
    } else {
        return withdraw(amount, accountBalance)
    }
}

// FUNCTION THAT HANDLES THE WITHDRAWAL FOR DEBIT ACCOUNT
fun debitWithdraw(amount: Double, accountBalance: DoubleArray): Double {
    if (accountBalance[0] == 0.0) {
        println("Can't withdraw, no money on this account!")
        return accountBalance[0]
    } else if (amount > accountBalance[0]) {
        println("Not enough money on this account! The current balance is R${accountBalance[0]} .")
        return 0.0
    } else {
        return withdraw(amount, accountBalance)
    }
}

// FUNCTION FOR IMPLEMENTING THE DEPOSIT OPERATION FOR CREDIT ACCOUNTS
fun creditDeposit(amount: Double, accountBalance: DoubleArray): Double {
    accountBalance[0] += amount
    println("You successfully deposited R$amount. The current balance is R${accountBalance[0]} .")
    return amount
}

fun main() {

    // ACCOUNT OPTIONS USER CAN CREATE
    println("Welcome to your banking system.")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    // VARIABLES FOR STORING ACCOUNT TYPE AND THE USERS CHOICE
    var accountType = ""
    var userChoice: Int

    // SCANNER FOR ACCEPTING USERS INPUT
    val scanner = Scanner(System.`in`)

    // LOOP FOR READING THE USERS INPUT AND ASSOCIATING IT WITH THE OPTIONS PROVIDED
    while (accountType == "") {
        println("Choose an option (1, 2 or 3)")
        userChoice = scanner.nextInt()
        println("The selected option is $userChoice.")

        accountType = when (userChoice) {
            1 -> "debit"
            2 -> "credit"
            3 -> "checking"
            else -> {
                println("Invalid option. Please select again.")
                ""
            }
        }
    }

    // OUTPUT OF TYPE OF ACCOUNT THE USER CREATED
    println("You have created a $accountType account.")

    // VARIABLES FOR ACCOUNT BALANCE AND THE MONEY THE USER WOULD LIKE TO WITHDRAW
    val accountBalance = doubleArrayOf(0.0)
    var moneyIn: Double

    // Putting money into the Account.
    println("how much would you like to deposit?")
    moneyIn = scanner.nextDouble()
    accountBalance[0] += moneyIn
    println("your balance is R$moneyIn")

    // TEST VARIABLE FOR THE OUTPUT OF THE BANK ACCOUNT OPERATION
    var output = 0.0
    var moneyOut: Double

    // Processing operations based on account type
    output = when (accountType) {
        "debit" -> {
            println("What would you like to do? Enter 1 to withdraw, 2 to deposit:")
            val actionChoice = scanner.nextInt()
            when (actionChoice) {
                1 -> {
                    println("how much would you like to withdraw?")
                    moneyOut = scanner.nextDouble()
                    debitWithdraw(moneyOut, accountBalance)
                }
                2 -> {
                    println("how much would you like to deposit?")
                    moneyIn = scanner.nextDouble()
                    accountBalance[0] += moneyIn
                    moneyIn
                }
                else -> {
                    println("Invalid choice.")
                    0.0
                }
            }
        }
        "credit" -> {
            println("What would you like to do? Enter 1 to withdraw, 2 to deposit:")
            val actionChoice = scanner.nextInt()
            when (actionChoice) {
                1 -> {
                    println("how much would you like to withdraw?")
                    moneyOut = scanner.nextDouble()
                    creditWithdraw(moneyOut, accountBalance)
                }
                2 -> {
                    println("how much would you like to deposit?")
                    moneyIn = scanner.nextDouble()
                    creditDeposit(moneyIn, accountBalance)
                }
                else -> {
                    println("Invalid choice.")
                    0.0
                }
            }
        }
        "checking" -> {
            println("Checking account doesn't support withdrawal operation.")
            println("What would you like to do? Enter 1 to deposit:")
            val actionChoice = scanner.nextInt()
            when (actionChoice) {
                1 -> {
                    println("how much would you like to deposit?")
                    moneyIn = scanner.nextDouble()
                    accountBalance[0] += moneyIn
                    moneyIn
                }
                else -> {
                    println("Invalid choice.")
                    0.0
                }
            }
        }
        else -> 0.0
    }
    println("The amount you withdrew/deposited is R$output.")
}

