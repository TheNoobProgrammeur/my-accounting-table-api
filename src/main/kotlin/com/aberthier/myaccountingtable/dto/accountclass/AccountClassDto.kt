package com.aberthier.myaccountingtable.dto.accountclass

import com.aberthier.myaccountingtable.dto.subAccountClass.SubAccountClassDto
import com.aberthier.myaccountingtable.dto.subAccountClass.toSubAccountClassDto
import com.aberthier.myaccountingtable.models.AccountClass
import com.aberthier.myaccountingtable.service.difference
import com.aberthier.myaccountingtable.service.income
import com.aberthier.myaccountingtable.service.outlay
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class AccountClassDto(
    @JsonProperty("id")
    var id: Long?,

    @JsonProperty("description")
    var description: String?,

    @JsonProperty("name")
    var name: String?,

    @JsonProperty("subsAccountClass")
    var accountClass: List<SubAccountClassDto>?,

    @JsonProperty("outlay")
    var outlay: BigDecimal,

    @JsonProperty("income")
    var income: BigDecimal,

    @JsonProperty("difference")
    var difference: BigDecimal,
)

fun AccountClass.toAccountClassDto() = run {
    AccountClassDto(
        id, description, name, getSubAccountClassDto(), outlay(), income(), difference()
    )
}

fun AccountClass.getSubAccountClassDto(): List<SubAccountClassDto> =
    run { subsAccountClass.map { subAccountClass -> subAccountClass.toSubAccountClassDto() } }
