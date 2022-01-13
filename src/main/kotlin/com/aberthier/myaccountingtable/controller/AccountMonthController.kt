package com.aberthier.myaccountingtable.controller

import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthCreateDto
import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthParams
import com.aberthier.myaccountingtable.service.AccountMonthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account/month")
class AccountMonthController(
    var accountMonthService: AccountMonthService
) {

    @PostMapping
    fun createAccountMonthService(@RequestBody params: AccountMonthParams): ResponseEntity<AccountMonthCreateDto> {
        return accountMonthService.initAccountMonth(
            params.userId,
            params.date
        )
    }

}
