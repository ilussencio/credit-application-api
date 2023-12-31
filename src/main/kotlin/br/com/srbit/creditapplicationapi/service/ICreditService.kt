package br.com.srbit.creditapplicationapi.service

import br.com.srbit.creditapplicationapi.model.Credit
import java.util.UUID

interface ICreditService {
    fun save(credit: Credit): Credit

    fun findAllByCustomer(customerId: Long): List<Credit>

    fun findByCreditCode(customerId: Long, creditCode: UUID): Credit
}