package com.aberthier.myaccountingtable.repository

import com.aberthier.myaccountingtable.models.MonthlyIncomes
import org.springframework.data.repository.CrudRepository

interface MonthlyIncomesRepository: CrudRepository<MonthlyIncomes, Long>