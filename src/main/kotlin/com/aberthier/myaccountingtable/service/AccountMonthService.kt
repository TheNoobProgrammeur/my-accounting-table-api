package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthCreateDto
import com.aberthier.myaccountingtable.dto.accountMonth.toAccountMonthCreateDto
import com.aberthier.myaccountingtable.exceptions.NotFondException
import com.aberthier.myaccountingtable.models.AccountMonth
import com.aberthier.myaccountingtable.repository.AccountMonthRepository
import com.aberthier.myaccountingtable.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.YearMonth

@Service
class AccountMonthService {
    @Autowired
    lateinit var accountMonthRepository: AccountMonthRepository

    @Autowired
    lateinit var userRepository: UserRepository


    fun initCurrentAccountMonth(): AccountMonth {
        val currentAccountMonth = AccountMonth()
        accountMonthRepository.save(currentAccountMonth)
        return currentAccountMonth
    }

    fun initAccountMonth(id: Long, date: YearMonth?): ResponseEntity<AccountMonthCreateDto> {
        val accountMonth: AccountMonth
        val userOpt = userRepository.findById(id)
        if (userOpt.isEmpty) {
            throw NotFondException()
        }
        val user = userOpt.get()
        if (date != null) {
            accountMonth = AccountMonth(date)
            accountMonthRepository.save(accountMonth)
        } else {
            accountMonth = initCurrentAccountMonth()
        }
        user.accountMonth?.put(accountMonth.date, accountMonth)
        val accountMonthDto = accountMonth.toAccountMonthCreateDto()
        return ResponseEntity<AccountMonthCreateDto>(accountMonthDto, HttpStatus.CREATED)
    }

}