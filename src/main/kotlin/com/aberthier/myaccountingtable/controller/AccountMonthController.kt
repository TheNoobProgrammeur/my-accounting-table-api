package com.aberthier.myaccountingtable.controller

import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthCreateDto
import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthDto
import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthParams
import com.aberthier.myaccountingtable.service.AccountMonthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.YearMonth

@RestController
@RequestMapping("/account/month")
class AccountMonthController(
    var accountMonthService: AccountMonthService
) {


    @GetMapping("/{id}")
    fun getAccountMonthService(
        @PathVariable("id") id: Long,
        @RequestParam("date") date: YearMonth? = null
    ): ResponseEntity<AccountMonthDto> {
        return accountMonthService.getAccountMont(id, date)
    }

    @PostMapping
    fun createAccountMonthService(@RequestBody params: AccountMonthParams): ResponseEntity<AccountMonthCreateDto> {
        return accountMonthService.initAccountMonth(
            params.userId,
            params.date
        )
    }

}
