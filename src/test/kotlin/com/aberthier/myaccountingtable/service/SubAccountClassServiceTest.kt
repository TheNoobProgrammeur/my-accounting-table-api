package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.models.MonetaryFlux
import com.aberthier.myaccountingtable.models.SubAccountClass
import com.aberthier.myaccountingtable.models.TypeFlux
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Spy
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class SubAccountClassServiceTest {
    @Spy
    @InjectMocks
    private var accountMontService = SubAccountClassService()

    private var subAccountClass = SubAccountClass("Sous class de test")

    @BeforeEach
    fun initSubAccountClass() {
        subAccountClass.listFlux?.clear()
        val fluxOne = MonetaryFlux(
            amount = BigDecimal(10.50),
            typeFlux = TypeFlux.INPUT
        )

        val fluxTwo = MonetaryFlux(
            amount = BigDecimal(0.50),
            typeFlux = TypeFlux.INPUT
        )

        val fluxThree = MonetaryFlux(
            amount = BigDecimal(10.00),
            typeFlux = TypeFlux.OUTPUT
        )

        subAccountClass.listFlux?.addAll(listOf(fluxOne, fluxTwo, fluxThree))
    }

    @Test
    fun outlayTest() {
        val out = accountMontService.outlay(subAccountClass)
        assertEquals(out.toInt(), 10)
    }

    @Test
    fun incomeTest() {
        val income = accountMontService.income(subAccountClass)
        assertEquals(income.toInt(), 11)
    }

    @Test
    fun differenceTest() {
        val diff = accountMontService.difference(subAccountClass)
        assertEquals(diff.toInt(), 1)
    }

}