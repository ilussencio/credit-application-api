package br.com.srbit.creditapplicationapi.controller

import br.com.srbit.creditapplicationapi.dto.CreditDto
import br.com.srbit.creditapplicationapi.dto.CreditView
import br.com.srbit.creditapplicationapi.dto.CreditViewList
import br.com.srbit.creditapplicationapi.model.Credit
import br.com.srbit.creditapplicationapi.service.impl.CreditService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credits")
class CreditController(
    private val creditService: CreditService
){
    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Credit ${credit.creditCode} - Customer ${credit.customer?.firstName}")
    }

    @GetMapping
    fun findAllByCustomerid(@RequestParam(value = "customerId") customerId: Long): ResponseEntity<List<CreditViewList>> {
        val list: List<CreditViewList> =  this.creditService.findAllByCustomer(customerId)
            .stream()
            .map{ credit: Credit -> CreditViewList(credit) }
            .collect(Collectors.toList())

        return ResponseEntity.status(HttpStatus.OK).body(list)
    }

    @GetMapping("/{creditCode}")
    fun findAllByCreditCode(@RequestParam(value= "customerId") customerId: Long,
                            @PathVariable creditCode: UUID): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(customerId, creditCode)

        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}