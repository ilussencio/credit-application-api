package br.com.srbit.creditapplicationapi.repositories

import br.com.srbit.creditapplicationapi.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long>{
}