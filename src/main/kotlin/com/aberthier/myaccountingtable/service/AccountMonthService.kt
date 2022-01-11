package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.models.AccountMonth
import com.aberthier.myaccountingtable.repository.AccountMonthRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AccountMonthService {
    @Autowired
    lateinit var accountMonthRepository: AccountMonthRepository

    fun initCurrentAccountMonth(): AccountMonth{
        val currentAccountMonth = AccountMonth()
        accountMonthRepository.save(currentAccountMonth)
        return currentAccountMonth
    }

}