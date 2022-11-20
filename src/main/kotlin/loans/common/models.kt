data class LumpSumPayment(
    var latestEmiNo: Int,
    var paymentAmount: Double
)

data class LoanInfo(
    val loanId: String, //= UUID.randomUUID().toString(),
    val bankName: String,
    val borrowerName: String,
    val principal: Double,
    val rateOfInterest: Double,
    val numYrs: Double,
    var totalLumpSumPaid: LumpSumPayment?
) {
    companion object {
        fun genLoanId(bankName: String, borrowerName: String) = "${bankName}-${borrowerName}".sha256()
    }
}


data class Balance(
    val amtPaid: Int,
    val numEmiLeft: Int
)
