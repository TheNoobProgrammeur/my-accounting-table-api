package com.aberthier.myaccountingtable.dto.accountMonth

import com.aberthier.myaccountingtable.dto.accountclass.AccountClassDto
import com.aberthier.myaccountingtable.dto.accountclass.toAccountClassDto
import com.aberthier.myaccountingtable.models.AccountMonth
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.YearMonth

data class AccountMonthDto(
    @JsonProperty("id")
    var id: Long? = null,

    @JsonProperty("date")
    var date: YearMonth? = null,

    @JsonProperty("accountsClass")
    var accountsClassDto: List<AccountClassDto>

)

fun AccountMonth.toDto() = run {
    AccountMonthDto(
        id, date,
        getAccountsClassDto()
    )
}

fun AccountMonth.getAccountsClassDto() = run { accountsClass.map { accountClass -> accountClass.toAccountClassDto() } }