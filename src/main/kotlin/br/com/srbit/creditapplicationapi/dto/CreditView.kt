package br.com.srbit.creditapplicationapi.dto

import java.math.BigDecimal
import java.util.UUID
import br.com.srbit.creditapplicationapi.enum.Status
import br.com.srbit.creditapplicationapi.model.Credit

data class CreditView(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallment: Int,
    val status: Status,
    val emailCustomer: String?,
    val incomeCustomer: BigDecimal?
){
    constructor(credit: Credit): this (
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallment = credit.numberOfInstallment,
        status = credit.status,
        emailCustomer = credit.customer?.email,
        incomeCustomer = credit.customer?.income
    )
}
