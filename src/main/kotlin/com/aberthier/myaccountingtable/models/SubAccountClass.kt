package com.aberthier.myaccountingtable.models

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "sub_account_class")
data class SubAccountClass(
    var name: String? = null,
    var description: String? = null,
    @OneToMany
    @JoinColumn(name = "id_sub_account")
    var listFlux: MutableList<MonetaryFlux>? = ArrayList<MonetaryFlux>(),
    @Id
    @GeneratedValue
    var id: Long? = null

)