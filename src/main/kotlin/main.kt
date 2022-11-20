import loans.SimpleInterestLoan

fun main(args: Array<String>) {
    val testFiles = listOf(
        "/Users/manu/dev/_interviews/navi/src/test/input_1.txt",
        "/Users/manu/dev/_interviews/navi/src/test/input_2.txt"
    )

    val loansManager = LoansManager(SimpleInterestLoan())
    val fileName = if (args.size > 1 && args[1].length > 0) args[1] else testFiles[1]

    loansManager.executeCommands(fileName = fileName)
}
