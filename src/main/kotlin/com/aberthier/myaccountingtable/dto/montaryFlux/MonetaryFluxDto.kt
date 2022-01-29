package com.aberthier.myaccountingtable.dto.montaryFlux

import com.aberthier.myaccountingtable.models.MonetaryFlux
import com.aberthier.myaccountingtable.models.TypeFlux
import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class MonetaryFluxDto(
    @JsonProperty("id")
    var id: Long?,
    @JsonProperty("name")
    var name: String?,
    @JsonProperty("detail")
    var detail: String?,
    @JsonProperty("typeFlux")
    var typeFlux: TypeFlux?,
    @JsonProperty("amount")
    var amount: BigDecimal?,
)

fun MonetaryFlux.toMonetaryFluxDto() = run { MonetaryFluxDto(id, name, detail, typeFlux, amount) }