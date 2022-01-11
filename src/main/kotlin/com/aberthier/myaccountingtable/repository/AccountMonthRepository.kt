package com.aberthier.myaccountingtable.repository

import com.aberthier.myaccountingtable.models.AccountMonth
import org.springframework.data.repository.CrudRepository

interface AccountMonthRepository: CrudRepository<AccountMonth, Long>