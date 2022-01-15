package com.aberthier.myaccountingtable.web

import com.aberthier.myaccountingtable.dto.ErrorResponseTestDto
import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthDto
import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthOperationDto
import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthParams
import com.aberthier.myaccountingtable.models.AccountMonth
import com.aberthier.myaccountingtable.models.User
import com.aberthier.myaccountingtable.repository.AccountMonthRepository
import com.aberthier.myaccountingtable.repository.UserRepository
import com.aberthier.myaccountingtable.util.TestUtil
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.net.URI
import java.time.YearMonth
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AccountMonthTest {
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @MockBean(name = "accountMonthRepository")
    lateinit var accountMonthRepository: AccountMonthRepository

    @MockBean(name = "userRepository")
    lateinit var userRepository: UserRepository

    @LocalServerPort
    var port: Int = 0

    private fun applicationUrl() = "http://localhost:$port"

    private final val user = TestUtil.generateSimpleUser()
    private final val accountMonth = TestUtil.generateSimpleMonthCount()
    private final val accountMonthAout = TestUtil.generateSimpleMonthCount(date = YearMonth.of(2021, 8))
    private final val route = "/account/month"

    @BeforeEach
    fun `init mocks`() {
        `when`(
            userRepository.findById(
                any()
            )
        ).thenReturn(Optional.of(user))

        `when`(
            userRepository.save(
                any()
            )
        ).thenAnswer {
            val callback = it.arguments[0] as User
            callback.id = 1
            callback
        }

        `when`(accountMonthRepository.save(any()))
            .thenAnswer {
                val callback = it.arguments[0] as AccountMonth
                callback.id = 1
                callback
            }

        `when`(
            accountMonthRepository.findById(
                any()
            )
        ).thenReturn(Optional.of(accountMonth))

        `when`(
            accountMonthRepository.findByUserIdAndDate(any(), any())
        ).thenAnswer {
            val date = it.arguments[1] as YearMonth
            if (date.month.value == 8) {
                return@thenAnswer Optional.of(accountMonthAout)
            } else if (date.month.value == YearMonth.now().monthValue) {
                return@thenAnswer Optional.of(accountMonth)
            }
            Optional.ofNullable(null)
        }
    }

    @Test
    fun `POST account month`() {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val body = AccountMonthParams(
            1
        )
        val requestEntity: HttpEntity<AccountMonthParams> = HttpEntity<AccountMonthParams>(body, headers)

        val url = URI(applicationUrl() + route)
        val res = restTemplate.postForEntity(url, requestEntity, AccountMonthOperationDto::class.java)

        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.CREATED)
        val m = res.body
        assertNotNull(m)
        assertEquals(m?.date, YearMonth.now())

    }

    @Test
    fun `POST account specific month`() {

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val body = AccountMonthParams(
            1,
            YearMonth.of(2019, 7)
        )
        val requestEntity: HttpEntity<AccountMonthParams> = HttpEntity<AccountMonthParams>(body, headers)

        val url = URI(applicationUrl() + route)
        val res = restTemplate.postForEntity(url, requestEntity, AccountMonthOperationDto::class.java)

        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.CREATED)
        val m = res.body
        assertNotNull(m)
        assertEquals(m?.date.toString(), "2019-07")
    }

    @Test
    fun `GET account month`() {
        val url = URI(applicationUrl() + route + "/${user.id}")
        val res = restTemplate.getForEntity(url, AccountMonthDto::class.java)
        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.OK)
        val month = res.body

        assertEquals(month?.id, 1)
        assertEquals(month?.date, YearMonth.now())
    }

    @Test
    fun `GET account month Aout`() {
        val url = URI(applicationUrl() + route + "/${user.id}?date=2021-08")
        val res = restTemplate.getForEntity(url, AccountMonthDto::class.java)
        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.OK)
        val month = res.body

        assertEquals(month?.id, 1)
        assertEquals(month?.date.toString(), "2021-08")
    }

    @Test
    fun `GET account month Not found`() {
        val url = URI(applicationUrl() + route + "/${user.id}?date=2021-10")
        val res = restTemplate.getForEntity(url, ErrorResponseTestDto::class.java)
        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.NOT_FOUND)
    }

}