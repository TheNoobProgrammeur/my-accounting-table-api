package com.aberthier.myaccountingtable.dto.subAccountClass

import com.aberthier.myaccountingtable.dto.montaryFlux.MonetaryFluxDto
import com.aberthier.myaccountingtable.dto.montaryFlux.toMonetaryFluxDto
import com.aberthier.myaccountingtable.models.SubAccountClass
import com.aberthier.myaccountingtable.service.difference
import com.aberthier.myaccountingtable.service.income
import com.aberthier.myaccountingtable.service.outlay
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class SubAccountClassDto(
    @JsonProperty("id")
    var id: Long?,

    @JsonProperty("name")
    var name: String?,

    @JsonProperty("description")
    var description: String?,

    @JsonProperty("flux")
    var listFlux: List<MonetaryFluxDto>?,

    @JsonProperty("outlay")
    var outlay: BigDecimal,

    @JsonProperty("income")
    var income: BigDecimal,

    @JsonProperty("difference")
    var difference: BigDecimal,
)

fun SubAccountClass.toSubAccountClassDto() = run {
    SubAccountClassDto(id, description, name, getListFluxDto(), outlay(), income(), difference())
}

fun SubAccountClass.getListFluxDto() = run { listFlux?.map { monetaryFlux -> monetaryFlux.toMonetaryFluxDto() } }