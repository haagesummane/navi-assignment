package loans.common.data

import LoanInfo
import LumpSumPayment
import kotlin.math.max


class LoanRegistry {
    private var loansRegistry: MutableMap<String, LoanInfo> = mutableMapOf()

    fun addLoan(loanInfo: LoanInfo) {
        loansRegistry[loanInfo.loanId] = loanInfo
    }

    fun getLoan(bankName: String, borrowerName: String): LoanInfo? {
        return loansRegistry[LoanInfo.genLoanId(bankName, borrowerName)]
    }

    fun getLoanId(bankName: String, borrowerName: String): String? {
        return loansRegistry[LoanInfo.genLoanId(bankName, borrowerName)]?.loanId
    }

    fun updateTotalLumpSum(loanId: String, payment: LumpSumPayment) {
        // maintain a running sum total paid and latest emi number

        loansRegistry[loanId] ?: return

        loansRegistry[loanId]?.totalLumpSumPaid ?: let {
            loansRegistry[loanId]?.totalLumpSumPaid = payment
            return
        }

        loansRegistry[loanId]!!.totalLumpSumPaid!!.paymentAmount += payment.paymentAmount
        loansRegistry[loanId]!!.totalLumpSumPaid!!.latestEmiNo =
            max(loansRegistry[loanId]!!.totalLumpSumPaid!!.latestEmiNo, payment.latestEmiNo)


    }
}
