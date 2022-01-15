package com.aberthier.myaccountingtable.web

import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthCreateDto
import com.aberthier.myaccountingtable.dto.accountMonth.AccountMonthParams
import com.aberthier.myaccountingtable.dto.user.UserCreateDto
import com.aberthier.myaccountingtable.dto.user.UserDto
import com.aberthier.myaccountingtable.models.AccountMonth
import com.aberthier.myaccountingtable.models.User
import com.aberthier.myaccountingtable.repository.AccountMonthRepository
import com.aberthier.myaccountingtable.repository.UserRepository
import com.aberthier.myaccountingtable.util.UserUtil
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.`when`
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
class AccountMonthTest{
    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @MockBean(name = "accountMonthRepository")
    lateinit var accountMonthRepository: AccountMonthRepository

    @MockBean(name = "userRepository")
    lateinit var userRepository: UserRepository

    @LocalServerPort
    var port: Int = 0

    private fun applicationUrl() = "http://localhost:$port"

    private final val user = UserUtil.generateSimpleUser()
    private final val route = "/account/month"

    @BeforeEach
    fun `init mocks`(){
        `when`(userRepository.findById(
            any(Long::class.java))
        ).thenAnswer {
            Optional.of(user)
        }.thenReturn(Optional.of(user))

        `when`(userRepository.save(
            any(User::class.java))
        ).thenAnswer {
            val callback = it.arguments[0] as User
            callback.id = 1
            callback
        }

        `when`(accountMonthRepository.save(any(AccountMonth::class.java)))
            .thenAnswer {
                val callback = it.arguments[0] as AccountMonth
                callback.id = 1
                callback
            }
    }

    @Test
    fun `POST account month`(){


        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val body = AccountMonthParams(
            1
        )
        val requestEntity: HttpEntity<AccountMonthParams> = HttpEntity<AccountMonthParams>(body, headers)

        val url = URI(applicationUrl() + route)
        val res = restTemplate.postForEntity(url, requestEntity, AccountMonthCreateDto::class.java)

        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.CREATED)
        val m =  res.body
        assertNotNull(m)
        assertEquals(m?.date, YearMonth.now())

    }

    @Test
    fun `POST account specific month`(){
        
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val body = AccountMonthParams(
            1,
            YearMonth.of( 2019, 7)
        )
        val requestEntity: HttpEntity<AccountMonthParams> = HttpEntity<AccountMonthParams>(body, headers)

        val url = URI(applicationUrl() + route)
        val res = restTemplate.postForEntity(url, requestEntity, AccountMonthCreateDto::class.java)

        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.CREATED)
        val m =  res.body
        assertNotNull(m)
        assertEquals(m?.date.toString(), "2019-07")

    }

}