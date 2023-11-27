package br.com.srbit.creditapplicationapi.controller

import br.com.srbit.creditapplicationapi.dto.CustomerDto
import br.com.srbit.creditapplicationapi.dto.CustomerUpdateDto
import br.com.srbit.creditapplicationapi.dto.CustomerView
import br.com.srbit.creditapplicationapi.model.Customer
import br.com.srbit.creditapplicationapi.service.impl.CustomerService
import org.apache.coyote.Response
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/customer")
class CustomerController(
    private val customerService: CustomerService
){
    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = this.customerService.save(customerDto.toEntity())
        return ResponseEntity.ok().body("Customer ${savedCustomer.email}")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer : Customer = this.customerService.findById(id)
        return ResponseEntity.ok().body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteById(@PathVariable id: Long) = this.customerService.delete(id)


    @PatchMapping
    fun updateCustomer(@RequestParam(value = "customerId") id: Long,
                       @RequestBody customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer: Customer = this.customerService.findById(id)
        val customerTo: Customer = customerUpdateDto.toEntity(customer)
        val customerUpdated = this.customerService.save(customerTo)
        return ResponseEntity.ok().body(CustomerView(customerUpdated))
    }
}