import loans.SimpleInterestLoan

fun main(args: Array<String>) {
    val loansManager = LoansManager(SimpleInterestLoan())
//    val fileName = "/Users/manu/dev/_interviews/navi/src/test/input_1.txt"
    val fileName = "/Users/manu/dev/_interviews/navi/src/test/input_2.txt"
    loansManager.executeCommands(fileName = fileName)
}
