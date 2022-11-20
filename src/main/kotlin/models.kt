import java.util.*

data class LoanInfo(
    val loanId: String, //= UUID.randomUUID().toString(),
    val bankName: String,
    val borrowerName: String,
    val principal: Double,
    val rateOfInterest: Double,
    val numYrs: Double
) {
    companion object {
        fun genLoanId(bankName: String, borrowerName: String) = "${bankName}-${borrowerName}".sha256()
    }
}

data class LumpSumPayment(
    val loanId: String,
//    val latestEmiNo: Int,
//    val paymentAmount: Double
    val lumpSumPayments: MutableMap<Int, Double> // latest emi num, payment amt
)
