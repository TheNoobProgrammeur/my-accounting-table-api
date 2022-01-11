package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.models.SubAccountClass
import com.aberthier.myaccountingtable.models.TypeFlux
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class SubAccountClassService {

    fun outlay(subAccountClass: SubAccountClass): BigDecimal {
        val outlay = BigDecimal(0)
        subAccountClass.listFlux.filter{ it.typeFlux == TypeFlux.OUTPUT }.map{ outlay.add(it.amount)}
        return outlay
    }
    fun income(subAccountClass: SubAccountClass): BigDecimal {
        val income = BigDecimal(0)
        subAccountClass.listFlux.filter { it.typeFlux == TypeFlux.INPUT }.map { income.add(it.amount) }
        return income
    }
    fun difference(subAccountClass: SubAccountClass): BigDecimal {
        return  income(subAccountClass) - outlay(subAccountClass)
    }

}