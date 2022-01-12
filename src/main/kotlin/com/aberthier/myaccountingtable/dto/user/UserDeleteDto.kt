package com.aberthier.myaccountingtable.dto.user

import com.aberthier.myaccountingtable.models.User
import com.fasterxml.jackson.annotation.JsonProperty

data class UserDeleteDto(
    @JsonProperty("message")
    var message: String = "USER DELETE"
)


fun User.toUserDeleteDto() = run { UserDeleteDto() }