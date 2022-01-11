package com.aberthier.myaccountingtable.models


import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Enumerated
import javax.persistence.EnumType


@Entity
@Table(name = "monetary_flux")
class MonetaryFlux (

    @Id
    @GeneratedValue
    var id: Long? = null,

    var amount: BigDecimal,
    @Enumerated(EnumType.STRING)
    var typeFlux: TypeFlux,
    var detail: String?

)