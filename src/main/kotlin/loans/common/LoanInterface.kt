package loans.common

import Balance

interface LoanInterface {
    fun initLoan(bankName: String, borrowerName: String, principal: Double, numYrs: Double, rateOfInterest: Double)
    fun processPaymentInfo(bankName: String, borrowerName: String, lumpSumPaymentAmt: Double, latestEmiNum: Int)
    fun getBalance(bankName: String, borrowerName: String, emiNum: Int): Balance
}
