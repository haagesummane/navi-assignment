package loans

import LoanInfo
import LumpSumPayment
import sha256
import java.lang.Exception

interface LoanInterface {
    fun initLoan(bankName: String, borrowerName: String, principal: Double, numYrs: Double, rateOfInterest: Double)
    fun processPaymentInfo(bankName: String, borrowerName: String, lumpSumPaymentAmt: Double, latestEmiNum: Int)
    fun getBalance(bankName: String, borrowerName: String, emiNum: Int)
}


class LoanRegistry() {
    private val loansRegistry: MutableMap<String, LoanInfo> = mutableMapOf()

    fun addLoan(loanInfo: LoanInfo) {
        loansRegistry[loanInfo.loanId] = loanInfo
    }

    fun getLoan(loanId: String): LoanInfo? {
        return loansRegistry[loanId]
    }

    fun getLoanId(bankName: String, borrowerName: String): String? {
        return loansRegistry[LoanInfo.genLoanId(bankName, borrowerName)]?.loanId
    }
}


abstract class AbstractLoan() : LoanInterface {
    companion object {
        val paymentsRegistry: MutableMap<String, LumpSumPayment> = mutableMapOf()
        val loansRegistry: LoanRegistry = LoanRegistry()

        fun checkValLteZero(numToCheck: Double, propertyName: String) {
            if (numToCheck <= 0)
                throw Exception("$propertyName cannot be zero or negative")
        }

        fun checkPrincipalVal(principal: Double) {
            checkValLteZero(principal, "Principal")
        }

        fun checkLumpSumPaymentVal(payment: Double) {
            checkValLteZero(payment, "Lump sum payment")
        }

        fun checkTenureVal(numYrs: Double) {
            checkValLteZero(numYrs, "Tenure of loan")
        }

        fun checkRateOfInterest(rateOfInterest: Double) {
            checkValLteZero(rateOfInterest, "Rate of Interest")
        }

        fun checkEmiVal(emiNum: Int) {
            if (emiNum < 0)
                throw Exception("emi number cannot be negative")
        }
    }

}

class SimpleInterestLoanManager() : AbstractLoan() {
    init {
        println("Initiating Simple Interest Loan Manager")
    }

    override fun initLoan(
        bankName: String, borrowerName: String, principal: Double,
        numYrs: Double, rateOfInterest: Double
    ) {
        checkPrincipalVal(principal)
        checkTenureVal(numYrs)
        checkRateOfInterest(rateOfInterest)

        loansRegistry.addLoan(
            LoanInfo(
                loanId = LoanInfo.genLoanId(bankName, borrowerName),
                bankName = bankName,
                borrowerName = borrowerName,
                principal = principal,
                numYrs = numYrs,
                rateOfInterest = rateOfInterest
            )
        )
    }

    override fun processPaymentInfo(
        bankName: String,
        borrowerName: String,
        lumpSumPaymentAmt: Double,
        latestEmiNum: Int
    ) {
        checkEmiVal(latestEmiNum)
        checkLumpSumPaymentVal(lumpSumPaymentAmt)


        TODO("Not yet implemented")
    }

    override fun getBalance(bankName: String, borrowerName: String, emiNum: Int) {
        checkEmiVal(emiNum)
        TODO("Not yet implemented")
    }

}
