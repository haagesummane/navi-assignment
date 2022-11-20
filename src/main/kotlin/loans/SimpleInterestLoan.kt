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
        val totalAmt = ceil(calculateInterest(loanInfo) + loanInfo.principal).toInt();
        val emiAmt = ceil(totalAmt / (loanInfo.numYrs * 12.0))

        val paymentViaEmi = emiNum * emiAmt;
        val totalLumpSumPaid = paymentsRegistry.getPaymentAmtTillEmi(loanInfo, emiNum)
        val totalAmtPaid = ceil(paymentViaEmi + totalLumpSumPaid).toInt()

        val outstanding = totalAmt - totalAmtPaid
        val outstandingNumEmi = ceil(outstanding / emiAmt).toInt()
        return Balance(amtRemaining = outstanding, amtPaid = totalAmtPaid, numEmiLeft = outstandingNumEmi)
    }

}


