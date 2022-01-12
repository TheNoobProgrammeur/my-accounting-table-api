package com.aberthier.myaccountingtable.dto.accountMonth

import java.time.YearMonth

data class AccountMonthCreateInfos(
    val date: YearMonth? = null,
    val userId: Long
)
