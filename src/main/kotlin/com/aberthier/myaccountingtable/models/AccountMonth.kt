package com.aberthier.myaccountingtable.models

import java.time.YearMonth
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "account_month")
class AccountMonth(
        @Id
        @GeneratedValue
        var id: Long? = null,

        var date: YearMonth = YearMonth.now(),

        @OneToMany
        @JoinColumn(name = "id_account_month")
        var monthlyIncomes: List<MonthlyIncomes> = ArrayList<MonthlyIncomes>(),


        @OneToMany
        @JoinColumn(name = "id_account_month")
        var accountClass: List<AccountClass> = ArrayList<AccountClass>(),
)