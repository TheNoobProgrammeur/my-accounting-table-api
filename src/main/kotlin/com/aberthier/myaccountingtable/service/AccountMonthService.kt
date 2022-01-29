package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthDto
import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthOperationDto
import com.aberthier.myaccountingtable.dto.accountMonth.toAccountMonthAddAccountClassDto
import com.aberthier.myaccountingtable.dto.accountMonth.toAccountMonthCreateDto
import com.aberthier.myaccountingtable.dto.accountMonth.toDto
import com.aberthier.myaccountingtable.exceptions.ConflictErrorException
import com.aberthier.myaccountingtable.exceptions.NotFondException
import com.aberthier.myaccountingtable.models.AccountClass
import com.aberthier.myaccountingtable.models.AccountMonth
import com.aberthier.myaccountingtable.repository.AccountClassRepository
import com.aberthier.myaccountingtable.repository.AccountMonthRepository
import com.aberthier.myaccountingtable.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.YearMonth

@Service
class AccountMonthService(
    val accountMonthRepository: AccountMonthRepository,
    val userRepository: UserRepository,
    val accountClassRepository: AccountClassRepository

) {

    fun addAccountClass(
        name: String,
        userId: Long,
        date: YearMonth? = YearMonth.now(),
        description: String?
    ): ResponseEntity<AccountMonthOperationDto> {
        val userOpt = userRepository.findById(userId)
        if (userOpt.isEmpty) {
            throw NotFondException()
        }
        val user = userOpt.get()

        val accountClass = AccountClass(name, description)
        accountClassRepository.save(accountClass)
        val accountMonth = user.accountMonth?.get(date ?: YearMonth.now()) ?: throw NotFondException()
        accountMonth.accountsClass.add(accountClass)
        accountMonthRepository.save(accountMonth)
        return ResponseEntity<AccountMonthOperationDto>(
            accountMonth.toAccountMonthAddAccountClassDto(name),
            HttpStatus.CREATED
        )

    }

    fun initCurrentAccountMonth(): AccountMonth {
        val currentAccountMonth = AccountMonth()
        accountMonthRepository.save(currentAccountMonth)
        return currentAccountMonth
    }

    fun initAccountMonth(id: Long, date: YearMonth? = null): ResponseEntity<AccountMonthOperationDto> {
        val accountMonth: AccountMonth
        val userOpt = userRepository.findById(id)
        if (userOpt.isEmpty) {
            throw NotFondException()
        }
        val user = userOpt.get()

        if (date != null && user.accountMonth?.get(date) != null) {
            throw ConflictErrorException()
        }

        if (date != null) {
            accountMonth = AccountMonth(date)
            accountMonthRepository.save(accountMonth)
        } else {
            accountMonth = initCurrentAccountMonth()
        }

        user.accountMonth?.put(accountMonth.date!!, accountMonth)
        userRepository.save(user)


        val accountMonthDto = accountMonth.toAccountMonthCreateDto()
        return ResponseEntity<AccountMonthOperationDto>(accountMonthDto, HttpStatus.CREATED)
    }

    fun getAccountMont(id: Long, date: YearMonth? = null): ResponseEntity<AccountMonthDto> {
        val dateSearch = date ?: YearMonth.now()
        val accountOp = accountMonthRepository.findByUserIdAndDate(id, dateSearch)
        if (accountOp.isEmpty) {
            throw NotFondException()
        }
        return ResponseEntity<AccountMonthDto>(accountOp.get().toDto(), HttpStatus.OK)
    }

}