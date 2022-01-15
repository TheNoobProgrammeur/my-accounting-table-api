package com.aberthier.myaccountingtable.repository

import com.aberthier.myaccountingtable.models.AccountMonth
import org.springframework.data.repository.CrudRepository
import java.time.YearMonth
import java.util.*

interface AccountMonthRepository: CrudRepository<AccountMonth, Long>{
    fun findByUserIdAndDate(id: Long, date: YearMonth): Optional<AccountMonth>
}