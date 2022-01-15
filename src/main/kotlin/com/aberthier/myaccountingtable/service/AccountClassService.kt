package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.exceptions.NotFondException
import com.aberthier.myaccountingtable.models.AccountClass
import com.aberthier.myaccountingtable.repository.AccountClassRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class AccountClassService(
    val accountClassRepository: AccountClassRepository
) {
    fun getOutlayByID(id: Long): BigDecimal {
        val account = getAccountClassById(id)
        return account.outlay()
    }

    fun getIncomeByID(id: Long): BigDecimal {
        val account = getAccountClassById(id)
        return account.income()
    }

    fun getDifferenceByID(id: Long): BigDecimal {
        val account = getAccountClassById(id)
        return account.difference()
    }

    private fun getAccountClassById(id: Long): AccountClass {
        val accountOpt = accountClassRepository.findById(id)
        if (accountOpt.isEmpty) {
            throw NotFondException()
        }
        return accountOpt.get()
    }
}

fun AccountClass.outlay() = run {
    var outlay = BigDecimal(0.00)
    subsAccountClass.map {
        outlay = outlay.add(it.outlay())
    }
    outlay
}

fun AccountClass.income() = run {
    var income = BigDecimal(0.00)
    subsAccountClass.map {
        income = income.add(it.income())
    }
    income
}

fun AccountClass.difference(): BigDecimal = run { income().subtract(outlay()) }