import loans.SimpleInterestLoan
import loans.common.LoanInterface
import java.io.File


enum class Commands {
    LOAN, PAYMENT, BALANCE
}

fun parseInput(line: String): Pair<Commands, List<String>>? {
    val values = line.split(' ').map { it.trim() }
    val commandStr = values.first().uppercase()
    val cmd = enumValues<Commands>().find { it.name == commandStr } ?: return null
    return Pair(cmd, values.subList(1, values.lastIndex + 1))
}

fun executeCommands(loanMgr: LoanInterface, fileName: String) {
    File(fileName).forEachLine { line ->
        run {
            parseInput(line)?.let {
                val (cmd, params) = it
                when (cmd) {
                    Commands.LOAN -> {
                        loanMgr.initLoan(
                            bankName = params[1], borrowerName = params[2], principal = params[3].toDouble(),
                            numYrs = params[4].toDouble(), rateOfInterest = params[5].toDouble()
                        )
                    }
                    Commands.PAYMENT -> {
                        loanMgr.processPaymentInfo(
                            bankName = params[1], borrowerName = params[2],
                            lumpSumPaymentAmt = params[3].toDouble(), latestEmiNum = params[4].toInt()
                        )
                    }
                    Commands.BALANCE -> {
                        loanMgr.getBalance(bankName = params[1], borrowerName = params[2], emiNum = params[3].toInt())
                            .let { bal -> println("${params[1]} ${params[2]} ${bal.amtPaid} ${bal.numEmiLeft} ") }
                    }

                }
            }
        }
    }
}

fun main(args: Array<String>) {
    executeCommands(loanMgr = SimpleInterestLoan(), fileName = "input.txt")
}
