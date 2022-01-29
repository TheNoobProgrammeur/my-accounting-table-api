package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.models.AccountMonth
import com.aberthier.myaccountingtable.repository.AccountClassRepository
import com.aberthier.myaccountingtable.repository.AccountMonthRepository
import com.aberthier.myaccountingtable.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.springframework.boot.test.context.SpringBootTest
import java.time.YearMonth

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class AccountMontServiceTest {

    @Mock
    lateinit var accountMonthRepository: AccountMonthRepository

    @Mock
    lateinit var accountClassRepository: AccountClassRepository

    @Mock
    lateinit var userRepository: UserRepository

    @Spy
    @InjectMocks
    private lateinit var accountMontService: AccountMonthService


    @Test
    fun `init Current AccountMonth Test`() {
        Mockito.`when`(accountMonthRepository.save(ArgumentMatchers.any(AccountMonth::class.java))).thenAnswer {
            val callback = it.arguments[0] as AccountMonth
            callback.id = 1
            callback
        }
        val currantDate = YearMonth.now()
        val currantAccountMonth = accountMontService.initCurrentAccountMonth()

        assertNotNull(currantAccountMonth)
        assertEquals(currantAccountMonth.date, currantDate)
    }
}