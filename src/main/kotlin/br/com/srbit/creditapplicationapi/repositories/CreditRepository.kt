package br.com.srbit.creditapplicationapi.repositories

import br.com.srbit.creditapplicationapi.model.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepository: JpaRepository<Credit, Long>{
}