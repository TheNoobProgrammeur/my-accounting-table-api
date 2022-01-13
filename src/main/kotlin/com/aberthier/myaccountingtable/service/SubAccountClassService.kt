package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.exceptions.NotFondException
import com.aberthier.myaccountingtable.models.SubAccountClass
import com.aberthier.myaccountingtable.models.TypeFlux
import com.aberthier.myaccountingtable.repository.SubAccountClassRepository
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class SubAccountClassService(
    val subAccountClassRepository: SubAccountClassRepository
) {

    fun getOutlayByID(id: Long): BigDecimal {
        val subAccount = getSubAccountClassById(id)
        return subAccount.outlay()
    }

    fun getIncomeByID(id: Long): BigDecimal {
        val subAccount = getSubAccountClassById(id)
        return subAccount.income()
    }

    fun getDifferenceByID(id: Long): BigDecimal {
        val subAccount = getSubAccountClassById(id)
        return subAccount.difference()
    }

    private fun getSubAccountClassById(id: Long): SubAccountClass {
        val subAccountOpt = subAccountClassRepository.findById(id)
        if (subAccountOpt.isEmpty) {
            throw NotFondException()
        }
        return subAccountOpt.get()
    }

}

fun SubAccountClass.outlay() = run {
    var outlay = BigDecimal(0.00)
    listFlux?.filter {
        it.typeFlux == TypeFlux.OUTPUT
    }?.map {
        outlay = outlay.add(it.amount)
    }
    outlay
}

fun SubAccountClass.income() = run {
    var income = BigDecimal(0.00)
    listFlux?.filter {
        it.typeFlux == TypeFlux.INPUT
    }?.map {
        income = income.add(it.amount)
    }
    income
}

fun SubAccountClass.difference() = run { income().subtract(outlay()) }