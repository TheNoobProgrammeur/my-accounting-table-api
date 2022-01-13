package com.aberthier.myaccountingtable.models

import java.time.YearMonth
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table
import javax.persistence.UniqueConstraint

@Entity
@Table(
    name = "account_month",
    uniqueConstraints = [UniqueConstraint(columnNames = arrayOf("date", "user_id"))]
)
data class AccountMonth(
    var date: YearMonth? = YearMonth.now(),
    @OneToMany
    @JoinColumn(name = "id_account_month")
    var monthlyIncomes: MutableList<MonthlyIncomes> = ArrayList<MonthlyIncomes>(),
    @OneToMany
    @JoinColumn(name = "id_account_month")
    var accountClass: List<AccountClass> = ArrayList<AccountClass>(),
    @Id
    @GeneratedValue
    var id: Long? = null,
)