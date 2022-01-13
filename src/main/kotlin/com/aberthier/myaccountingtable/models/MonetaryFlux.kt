package com.aberthier.myaccountingtable.models


import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "monetary_flux")
data class MonetaryFlux(
    var amount: BigDecimal? = null,
    @Enumerated(EnumType.STRING)
    var typeFlux: TypeFlux,
    var nom: String? = "Not specified",
    var detail: String? = null,
    @Id
    @GeneratedValue
    var id: Long? = null,
)