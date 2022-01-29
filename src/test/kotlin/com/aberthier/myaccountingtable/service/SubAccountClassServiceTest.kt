package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.models.MonetaryFlux
import com.aberthier.myaccountingtable.models.SubAccountClass
import com.aberthier.myaccountingtable.models.TypeFlux
import com.aberthier.myaccountingtable.repository.SubAccountClassRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.springframework.boot.test.context.SpringBootTest
import java.math.BigDecimal
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class SubAccountClassServiceTest {

    @Mock
    lateinit var subAccountClassRepository: SubAccountClassRepository

    @Spy
    @InjectMocks
    private lateinit var subAccountClassService: SubAccountClassService

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
        Mockito.`when`(subAccountClassRepository.findById(1)).thenAnswer {
            Optional.of(subAccountClass)
        }
    }

    @Test
    fun `outlay Test`() {
        val out = subAccountClassService.getOutlayByID(1)
        assertEquals(out.toInt(), 10)
    }

    @Test
    fun `income Test`() {
        val income = subAccountClassService.getIncomeByID(1)
        assertEquals(income.toInt(), 11)
    }

    @Test
    fun `difference Test`() {
        val diff = subAccountClassService.getDifferenceByID(1)
        assertEquals(diff.toInt(), 1)
    }

}