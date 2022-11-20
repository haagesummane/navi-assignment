package loans.common

import LoanInfo
import LumpSumPayment


class PaymentsRegistry {
    private val paymentsRegistry: MutableMap<String, MutableMap<Int, Double>> = mutableMapOf()

    fun addPayment(loanId: String, payment: LumpSumPayment) {
        paymentsRegistry[loanId]?.put(payment.latestEmiNo, payment.paymentAmount)
    }

    fun getPaymentAmtTillEmi(loanInfo: LoanInfo, emiNum: Int): Double {
        loanInfo.totalLumpSumPaid?.let { paymentsSoFar ->
            if (paymentsSoFar.latestEmiNo <= emiNum)
                return paymentsSoFar.paymentAmount
        }
        return (paymentsRegistry[loanInfo.loanId]?.filter { it.key <= emiNum }?.map { it.value }?.sum()) ?: 0.0
    }

//    fun getPaymentsTillEmi(loanId: String, emiNum: Int): List<LumpSumPayment>? {
//        return paymentsRegistry[loanId]?.filter { it.key <= emiNum }?.map { LumpSumPayment(it.key, it.value) }
//    }
}
