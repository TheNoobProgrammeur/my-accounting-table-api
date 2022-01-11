package com.aberthier.myaccountingtable.repository

import com.aberthier.myaccountingtable.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<User, Long>