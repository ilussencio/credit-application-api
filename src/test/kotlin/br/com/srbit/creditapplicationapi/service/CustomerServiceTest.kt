package br.com.srbit.creditapplicationapi.service

import br.com.srbit.creditapplicationapi.exception.BusinessException
import br.com.srbit.creditapplicationapi.model.Address
import br.com.srbit.creditapplicationapi.model.Customer
import br.com.srbit.creditapplicationapi.repositories.CustomerRepository
import br.com.srbit.creditapplicationapi.service.impl.CustomerService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.runs
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.util.*

//
@ExtendWith(MockKExtension::class)
class CustomerServiceTest {
    @MockK
    lateinit var customerRepository: CustomerRepository
    @InjectMockKs lateinit var customerService: CustomerService

    @Test
    fun `save customer`() {
        // given
        val fakeCustomer: Customer = buildCustomer()
        every { customerRepository.save(any()) } returns fakeCustomer
        // when
        val actual: Customer = customerService.save(fakeCustomer)
        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.save(any()) }
    }

    @Test
    fun `should fin customer by id`() {
        // given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer : Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(any())} returns Optional.of(fakeCustomer)

        //when
        val actual: Customer = customerService.findById(fakeId)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isExactlyInstanceOf(Customer::class.java)
        Assertions.assertThat(actual).isSameAs(fakeCustomer)
        verify(exactly = 1) { customerRepository.findById(any()) }
    }

    @Test
    fun `should not find customer by id and throw exception`() {
        // given
        val fakeId: Long = Random().nextLong()
        every { customerRepository.findById(any())} returns Optional.empty()

        //when
        //then
        Assertions.assertThatExceptionOfType(BusinessException::class.java)
            .isThrownBy { customerService.findById(fakeId) }
            .withMessage("Id $fakeId não encontrado")

        verify(exactly = 1) { customerRepository.findById(fakeId) }
    }

    @Test
    fun `should delete customer by id`(){
        //given
        val fakeId: Long = Random().nextLong()
        val fakeCustomer : Customer = buildCustomer(id = fakeId)
        every { customerRepository.findById(fakeId)} returns Optional.of(fakeCustomer)
        every { customerRepository.delete(fakeCustomer)} just runs
        // when
        customerService.delete(fakeId)

        //then
        verify(exactly = 1) { customerRepository.findById(fakeId) }
        verify(exactly = 1) { customerRepository.delete(fakeCustomer) }
    }

    private fun buildCustomer(
        firstName: String = "Patricia",
        lastName: String = "Santana Santiago",
        cpf: String = "48246576445",
        email: String = "patricia.santana@email.com",
        password: String = "123456",
        zipCode: String = "12345678",
        street: String = "Rua das Flores",
        income: BigDecimal = BigDecimal.valueOf(1000.00),
        id: Long = 1L
    ) = Customer(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        password = password,
        address = Address(
            zipCode = zipCode,
            street = street
        ),
        income = income,
        id = id
    )

    companion object

}