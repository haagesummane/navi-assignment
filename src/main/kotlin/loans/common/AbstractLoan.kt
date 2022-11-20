package loans.common

import Balance
import LoanInfo
import LumpSumPayment
import java.lang.Exception


abstract class AbstractLoan : LoanInterface {
    companion object {
        val paymentsRegistry: PaymentsRegistry = PaymentsRegistry()
        val loansRegistry: LoanRegistry = LoanRegistry()

        fun valLteZeroError(numToCheck: Double, propertyName: String) {
            if (numToCheck <= 0)
                throw Exception("$propertyName cannot be zero or negative")
        }

        fun checkEmiVal(emiNum: Int) {
            if (emiNum < 0)
                throw Exception("emi number cannot be negative")
        }
    }

    override fun initLoan(
        bankName: String, borrowerName: String, principal: Double,
        numYrs: Double, rateOfInterest: Double
    ) {
        valLteZeroError(principal, "Principal")
        valLteZeroError(numYrs, "Tenure of loan")
        valLteZeroError(rateOfInterest, "Rate of Interest")

        loansRegistry.addLoan(
            LoanInfo(
                loanId = LoanInfo.genLoanId(bankName, borrowerName),
                bankName = bankName,
                borrowerName = borrowerName,
                principal = principal,
                numYrs = numYrs,
                rateOfInterest = rateOfInterest,
                totalLumpSumPaid = null
            )
        )
    }

    override fun processPaymentInfo(
        bankName: String, borrowerName: String, lumpSumPaymentAmt: Double,
        latestEmiNum: Int
    ) {
        checkEmiVal(latestEmiNum)
        valLteZeroError(lumpSumPaymentAmt, "Lump sum payment")

        val payment = LumpSumPayment(latestEmiNum, lumpSumPaymentAmt)

        val loanId = loansRegistry.getLoanId(bankName, borrowerName)
            ?: throw Exception("Loan from $bankName to $borrowerName not found")

        paymentsRegistry.addPayment(loanId, payment)
        loansRegistry.updateTotalLumpSum(loanId, payment)
    }

    override fun getBalance(bankName: String, borrowerName: String, emiNum: Int): Balance {
        checkEmiVal(emiNum)
        val loanInfo = loansRegistry.getLoan(bankName, borrowerName) ?: return Balance(0, -1)
        return calculateOutstanding(loanInfo, emiNum)
    }

    abstract fun calculateInterest(loanInfo: LoanInfo): Double
    abstract fun calculateOutstanding(loanInfo: LoanInfo, emiNum: Int): Balance
}
