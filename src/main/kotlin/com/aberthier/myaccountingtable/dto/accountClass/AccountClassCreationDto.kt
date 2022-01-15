package com.aberthier.myaccountingtable.dto.accountClass

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.YearMonth

data class AccountClassCreationDto(
    @JsonProperty("name")
    var name: String,

    @JsonProperty("userId")
    var userId: Long,

    @JsonProperty("description")
    var description: String?,

    @JsonProperty("date")
    val date: YearMonth?
)
