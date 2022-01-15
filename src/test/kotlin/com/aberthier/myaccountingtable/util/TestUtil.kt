package com.aberthier.myaccountingtable.util

import com.aberthier.myaccountingtable.models.AccountClass
import com.aberthier.myaccountingtable.models.AccountMonth
import com.aberthier.myaccountingtable.models.MonthlyIncomes
import com.aberthier.myaccountingtable.models.User
import java.time.YearMonth

class TestUtil {
    companion object {
        fun generateSimpleUser(
            userLastName: String = "lastname",
            userFirstName: String = "firstName",
            userId: Long = 1
        ): User {
            return User(userFirstName, userLastName, id = userId)
        }

        fun generateSimpleMonthCount(
            date: YearMonth = YearMonth.now(),
            monthlyIncomes: MutableList<MonthlyIncomes> = ArrayList(),
            accountsClass: MutableList<AccountClass> = ArrayList(),
            id: Long = 1,
            userId: Long = 1,
        ): AccountMonth {
            return AccountMonth(
                date,
                monthlyIncomes,
                accountsClass,
                id, userId
            )
        }
    }
}