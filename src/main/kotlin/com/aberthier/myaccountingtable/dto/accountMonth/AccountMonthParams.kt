package com.aberthier.myaccountingtable.dto.accountMonth

import java.time.YearMonth

data class AccountMonthParams(
    val userId: Long,
    val date: YearMonth? = null
)
