package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.models.SubAccountClass
import com.aberthier.myaccountingtable.models.TypeFlux
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class SubAccountClassService {

    fun outlay(subAccountClass: SubAccountClass): BigDecimal {
        var outlay = BigDecimal(0.00)
        subAccountClass.listFlux?.filter {
            it.typeFlux == TypeFlux.OUTPUT
        }?.map {
            outlay = outlay.add(it.amount)
        }
        return outlay
    }

    fun income(subAccountClass: SubAccountClass): BigDecimal {
        var income = BigDecimal(0.00)
        subAccountClass.listFlux?.filter {
            it.typeFlux == TypeFlux.INPUT
        }?.map {
            income = income.add(it.amount)
        }
        return income
    }

    fun difference(subAccountClass: SubAccountClass): BigDecimal {
        return income(subAccountClass).subtract(outlay(subAccountClass))
    }

}