package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.dto.user.UserCreateDto
import com.aberthier.myaccountingtable.dto.user.UserDeleteDto
import com.aberthier.myaccountingtable.dto.user.UserDto
import com.aberthier.myaccountingtable.dto.user.toUser
import com.aberthier.myaccountingtable.dto.user.toUserCreateDto
import com.aberthier.myaccountingtable.dto.user.toUserDto
import com.aberthier.myaccountingtable.exceptions.NotFondException
import com.aberthier.myaccountingtable.models.AccountMonth
import com.aberthier.myaccountingtable.models.User
import com.aberthier.myaccountingtable.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    @Autowired
    var userRepository: UserRepository,

    @Autowired
    var accountMonthService: AccountMonthService
) {

    fun findUserById(id: Long): ResponseEntity<UserDto> {
        val optUser: Optional<User> = userRepository.findById(id)
        if (optUser.isPresent) {
            val user = optUser.get()
            return ResponseEntity<UserDto>(user.toUserDto(), HttpStatus.OK)
        } else {
            throw NotFondException()
        }
    }

    fun createUser(user: UserDto): ResponseEntity<UserCreateDto> {
        val currentAccountMonth: AccountMonth = accountMonthService.initCurrentAccountMonth()
        val savedUser = user.toUser()
        savedUser.accountMonth?.put(currentAccountMonth.date!!, currentAccountMonth)
        userRepository.save(savedUser)
        return ResponseEntity(savedUser.toUserCreateDto(), HttpStatus.CREATED)
    }

    fun deleteUser(id: Long): ResponseEntity<UserDeleteDto> {
        userRepository.deleteById(id)
        return ResponseEntity(UserDeleteDto(), HttpStatus.OK)
    }
}