package com.aberthier.myaccountingtable.dto.user

import com.aberthier.myaccountingtable.models.User
import com.fasterxml.jackson.annotation.JsonProperty

data class UserCreateDto(
    @JsonProperty("id")
    var id: Long? = null,
    @JsonProperty("message")
    var message: String = "USER CREATED"
)


fun User.toUserCreateDto() = run { UserCreateDto(id) }