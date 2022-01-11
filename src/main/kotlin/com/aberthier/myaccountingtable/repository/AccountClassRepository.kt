package com.aberthier.myaccountingtable.repository

import com.aberthier.myaccountingtable.models.AccountClass
import org.springframework.data.repository.CrudRepository

interface AccountClassRepository: CrudRepository<AccountClass, Long>