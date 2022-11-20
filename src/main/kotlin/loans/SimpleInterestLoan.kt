package loans

import Balance
import LoanInfo
import loans.common.AbstractLoan
import kotlin.math.ceil

class SimpleInterestLoan : AbstractLoan() {

    override fun calculateInterest(loanInfo: LoanInfo): Double {
        return loanInfo.principal * loanInfo.numYrs * loanInfo.rateOfInterest
    }

    override fun calculateOutstanding(loanInfo: LoanInfo, emiNum: Int): Balance {
        val totalAmt = calculateInterest(loanInfo) + loanInfo.principal;
        val emiAmt = ceil(totalAmt / (loanInfo.numYrs * 12.0))

        val paymentViaEmi = emiNum * emiAmt;
        val totalLumpSumPaid = paymentsRegistry.getPaymentAmtTillEmi(loanInfo, emiNum)
        val totalAmtPaid = ceil(paymentViaEmi + totalLumpSumPaid).toInt()

        val outstanding = totalAmt - totalAmtPaid
        val outstandingNumEmi = ceil(outstanding / emiAmt).toInt()
        return Balance(amtPaid = totalAmtPaid, numEmiLeft = outstandingNumEmi)
    }

}


