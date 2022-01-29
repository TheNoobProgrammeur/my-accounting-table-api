package com.aberthier.myaccountingtable.dto.user

import com.aberthier.myaccountingtable.models.User
import com.fasterxml.jackson.annotation.JsonProperty

data class UserDto(
    @JsonProperty("firstname")
    var firstname: String,
    @JsonProperty("lastname")
    var lastname: String,
    @JsonProperty("id")
    var id: Long? = null,
)

fun User.toUserDto() = run { UserDto(firstname, lastname, id) }
fun UserDto.toUser() = run { User(firstname, lastname, id = id) }