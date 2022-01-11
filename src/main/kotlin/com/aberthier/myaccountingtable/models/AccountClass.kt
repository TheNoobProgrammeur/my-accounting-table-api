package com.aberthier.myaccountingtable.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.OneToMany
import javax.persistence.JoinColumn

@Entity
@Table(name = "account_class")
class AccountClass (

        var name: String,
        var description: String,

        @OneToMany
        @JoinColumn(name = "id_account")
        var  subsAccountClass: List<SubAccountClass>,

        @Id
        @GeneratedValue
        var id: Long? = null,
)