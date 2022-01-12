package com.aberthier.myaccountingtable.controller

import com.aberthier.myaccountingtable.controller.dto.ResponseUserDto
import com.aberthier.myaccountingtable.controller.dto.UserDto
import com.aberthier.myaccountingtable.models.User
import com.aberthier.myaccountingtable.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping( "/user")
class UserController(
    @Autowired val userService: UserService
) {

    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Long ): ResponseEntity<User> {
        return userService.findUserById(id)
    }

    @PostMapping
    fun postUser(@RequestBody user: UserDto): ResponseEntity<ResponseUserDto> {
        return userService.createUser(user)
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Long): ResponseEntity<ResponseUserDto> {
        return userService.deleteUser(id)
    }
}