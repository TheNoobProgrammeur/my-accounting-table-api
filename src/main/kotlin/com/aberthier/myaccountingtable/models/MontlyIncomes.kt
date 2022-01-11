package com.aberthier.myaccountingtable.models

import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "monthly_incomes")
class MonthlyIncomes (
    @Id
    @GeneratedValue
    var id: Long? = null,

    var name: String,
    var details: String? = null,

    var actual: BigDecimal,
    var planed: BigDecimal? = null,
    @Enumerated(EnumType.STRING)
    var type: MontyIncomesType
)