package com.aberthier.myaccountingtable.dto

import lombok.Data

@Data
class UserDto(
    var firstname: String,
    var lastname: String
)


@Data
class ResponseUserDto(var id: Long, var message: String)