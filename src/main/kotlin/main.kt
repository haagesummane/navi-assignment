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
                            bankName = params[0], borrowerName = params[1], principal = params[2].toDouble(),
                            numYrs = params[3].toDouble(), rateOfInterest = params[4].toDouble()
                        )
                    }
                    Commands.PAYMENT -> {
                        loanMgr.processPaymentInfo(
                            bankName = params[0], borrowerName = params[1],
                            lumpSumPaymentAmt = params[2].toDouble(), latestEmiNum = params[3].toInt()
                        )
                    }
                    Commands.BALANCE -> {
                        loanMgr.getBalance(bankName = params[0], borrowerName = params[1], emiNum = params[2].toInt())
                            .let { bal -> println("${params[0]} ${params[1]} ${bal.amtPaid} ${bal.numEmiLeft} ") }
                    }

                }
            }
        }
    }
}

fun main(args: Array<String>) {
    executeCommands(
        loanMgr = SimpleInterestLoan(),
        fileName = "/Users/manu/dev/_interviews/navi/src/main/kotlin/input.txt"
    )
}
