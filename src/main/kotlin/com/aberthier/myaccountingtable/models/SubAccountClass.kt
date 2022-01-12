package com.aberthier.myaccountingtable.models

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Data
@NoArgsConstructor
@Table(name = "sub_account_class")
class SubAccountClass(

    var name: String,
    var description: String? = null,

    @OneToMany
    @JoinColumn(name = "id_sub_account")
    var listFlux: MutableList<MonetaryFlux>? = ArrayList<MonetaryFlux>(),

    @Id
    @GeneratedValue
    var id: Long? = null

)