package com.aberthier.myaccountingtable.service

import com.aberthier.myaccountingtable.controller.dto.ResponseUserDto
import com.aberthier.myaccountingtable.controller.dto.UserDto
import com.aberthier.myaccountingtable.exceptions.NotFondException
import com.aberthier.myaccountingtable.models.User
import com.aberthier.myaccountingtable.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(val userRepository: UserRepository) {

    fun  findUserById(id: Long): ResponseEntity<User>  {
        val optUser: Optional<User> = userRepository.findById(id)
        if (optUser.isPresent){
            val user = optUser.get()
            return  ResponseEntity<User>(user, HttpStatus.OK)
        } else {
            throw NotFondException()
        }
    }
    fun createUser(user: UserDto):  ResponseEntity<ResponseUserDto> {
        val savedUser: User = User(
                user.firstname,
                user.lastname
        )
        userRepository.save(savedUser)
        return ResponseEntity(ResponseUserDto(savedUser.id!!, "User create") , HttpStatus.CREATED)

    }
    fun deleteUser(id: Long):  ResponseEntity<ResponseUserDto> {
        userRepository.deleteById(id)
        return ResponseEntity(ResponseUserDto(id,"User delete"), HttpStatus.OK)
    }
}