package com.aberthier.myaccountingtable.dto.accountMonth

import com.aberthier.myaccountingtable.models.AccountMonth
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.YearMonth

data class AccountMonthOperationDto(
    @JsonProperty("id")
    var id: Long? = null,

    @JsonProperty("date")
    var date: YearMonth? = null,

    @JsonProperty("message")
    var message: String
)

fun AccountMonth.toAccountMonthCreateDto() = run { AccountMonthOperationDto(id, date, "CREATED") }
fun AccountMonth.toAccountMonthAddAccountClassDto(nameAccountClass: String) =
    run { AccountMonthOperationDto(id, date, "$nameAccountClass Account class adding") }