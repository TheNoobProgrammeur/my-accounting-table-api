package com.aberthier.myaccountingtable.dto.accountMonth

import com.aberthier.myaccountingtable.models.AccountMonth
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.YearMonth

data class AccountMonthCreateDto(
    @JsonProperty("id")
    var id: Long? = null,

    @JsonProperty("date")
    var date: YearMonth? = null,

    @JsonProperty("message")
    var message: String = "CREATED"
)

fun AccountMonth.toAccountMonthCreateDto() = run { AccountMonthCreateDto(id, date) }