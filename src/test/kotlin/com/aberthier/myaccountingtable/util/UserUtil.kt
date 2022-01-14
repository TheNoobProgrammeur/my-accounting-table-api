package com.aberthier.myaccountingtable.util

import com.aberthier.myaccountingtable.models.User

class UserUtil {
    companion object {
        fun generateSimpleUser (
            userLastName: String = "lastname",
            userFirstName: String = "firstName",
            userId: Long = 1
        ): User {
            return  User(userFirstName, userLastName, id = userId)
        }
    }
}