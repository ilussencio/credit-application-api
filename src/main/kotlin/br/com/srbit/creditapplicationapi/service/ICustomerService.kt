package br.com.srbit.creditapplicationapi.service

import br.com.srbit.creditapplicationapi.model.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer
    fun findById(id: Long): Customer
    fun delete(id: Long)

}