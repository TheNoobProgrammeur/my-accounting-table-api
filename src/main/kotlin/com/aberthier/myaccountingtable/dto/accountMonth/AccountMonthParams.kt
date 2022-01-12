package com.aberthier.myaccountingtable.dto.accountMonth

import java.time.YearMonth

data class AccountMonthParams(
    val date: YearMonth? = null,
    val userId: Long
)
