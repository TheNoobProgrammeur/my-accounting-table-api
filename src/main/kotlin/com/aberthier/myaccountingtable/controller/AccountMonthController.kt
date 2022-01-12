package com.aberthier.myaccountingtable.controller

import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthCreateDto
import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthCreateInfos
import com.aberthier.myaccountingtable.service.AccountMonthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account/month")
class AccountMonthController {
    @Autowired
    lateinit var accountMonthService: AccountMonthService

    @PostMapping
    fun createAccountMonthService(@RequestBody accountMonthCreateInfos: AccountMonthCreateInfos): ResponseEntity<AccountMonthCreateDto> {
        return accountMonthService.initAccountMonth(
            accountMonthCreateInfos.userId,
            accountMonthCreateInfos.date
        )
    }

}
