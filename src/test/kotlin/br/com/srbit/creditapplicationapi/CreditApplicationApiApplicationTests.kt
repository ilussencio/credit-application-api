package br.com.srbit.creditapplicationapi

import br.com.srbit.creditapplicationapi.repositories.CustomerRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CreditApplicationApiApplicationTests {
	@Autowired lateinit var customerRepository: CustomerRepository

	@Test
	fun contextLoads() {
	}

}
