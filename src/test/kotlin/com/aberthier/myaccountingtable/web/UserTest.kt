package com.aberthier.myaccountingtable.web

import com.aberthier.myaccountingtable.controller.dto.ResponseUserDto
import com.aberthier.myaccountingtable.controller.dto.UserDto
import com.aberthier.myaccountingtable.dto.ErrorResponseTestDto
import com.aberthier.myaccountingtable.models.User
import com.aberthier.myaccountingtable.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import java.net.URI
import java.util.*


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @MockBean(name = "userRepository")
    lateinit var userRepository: UserRepository

    @LocalServerPort
    var port: Int = 0

    private fun applicationUrl() = "http://localhost:$port"
    private final val userLastName = "lastname"
    private final val userFirstName = "firstName"
    private final val userId = 1L
    val user = User(userFirstName, userLastName, id = userId)


    @Test
    fun tesGETUser() {
        `when`(userRepository.findById(1)).thenReturn(Optional.of(user))


        val url = URI(applicationUrl() + "/user/1")
        val res = restTemplate.getForEntity(url, User::class.java)

        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.OK)
        val resUser = res.body
        assertEquals(resUser?.id, 1)
        assertNotNull(resUser?.accountMonth)
    }

    @Test
    fun tesGETNotFondUser() {

        val url = URI(applicationUrl() + "/user/1")
        val res = restTemplate.getForEntity(url, ErrorResponseTestDto::class.java)

        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.NOT_FOUND)
        val resBody = res.body
        assertEquals(resBody?.code, HttpStatus.NOT_FOUND.value())
        assertEquals(resBody?.message, "Resource not found")
    }

    @Test
    fun testPOSTUser() {
        `when`(userRepository.save(any(User::class.java))).thenAnswer {
            val callback = it.arguments[0] as User
            callback.id = 1
            callback
        }

        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON

        val body = UserDto(userFirstName, userLastName)
        val requestEntity: HttpEntity<UserDto> = HttpEntity<UserDto>(body, headers)

        val url = URI(applicationUrl() + "/user")
        val res = restTemplate.postForEntity(url, requestEntity, ResponseUserDto::class.java)

        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.CREATED)
        val resBody = res.body
        assertEquals(resBody?.id, 1)
        assertEquals(resBody?.message, "User create")
    }

    @Test
    fun testDELETEUser() {
        `when`(userRepository.deleteById(any(Long::class.java))).thenAnswer {
            null
        }

        val url = URI(applicationUrl() + "/user/1")
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val requestEntity: HttpEntity<ResponseUserDto> = HttpEntity<ResponseUserDto>(headers)
        val res = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, ResponseUserDto::class.java)

        assertNotNull(res)
        assertEquals(res.statusCode, HttpStatus.OK)
        val resUser = res.body
        assertEquals(resUser?.id, 1)
    }
}