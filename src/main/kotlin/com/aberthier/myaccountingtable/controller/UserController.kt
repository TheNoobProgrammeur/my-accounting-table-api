package com.aberthier.myaccountingtable.controller

import com.aberthier.myaccountingtable.dto.ResponseUserDto
import com.aberthier.myaccountingtable.dto.UserDto
import com.aberthier.myaccountingtable.models.User
import com.aberthier.myaccountingtable.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @Autowired
    lateinit var userService: UserService

    @GetMapping("/{id}")
    fun getUser(@PathVariable("id") id: Long): ResponseEntity<User> {
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