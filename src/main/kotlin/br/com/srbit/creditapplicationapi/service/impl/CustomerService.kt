package br.com.srbit.creditapplicationapi.service.impl

import br.com.srbit.creditapplicationapi.exception.BusinessException
import br.com.srbit.creditapplicationapi.model.Customer
import br.com.srbit.creditapplicationapi.repositories.CustomerRepository
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import br.com.srbit.creditapplicationapi.service.ICustomerService as ICustomerService1

@Service
class CustomerService(
    private val customerRepository: CustomerRepository
): ICustomerService1 {
    override fun save(customer: Customer): Customer {
        return this.customerRepository.save(customer)
    }

    override fun findById(id: Long): Customer = this.customerRepository.findById(id).orElseThrow(){
            throw BusinessException("Id $id não encontrado")
    }
    override fun delete(id: Long) {
        val customer: Customer = this.findById(id)
        this.customerRepository.delete(customer)
    }
}