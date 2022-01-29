package com.aberthier.myaccountingtable.models

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "monthly_incomes")
data class MonthlyIncomes(
    var name: String? = null,
    var details: String? = null,
    var actual: BigDecimal,
    var planed: BigDecimal? = null,
    @Enumerated(EnumType.STRING)
    var type: MontyIncomesType,
    @Id
    @GeneratedValue
    var id: Long? = null
)