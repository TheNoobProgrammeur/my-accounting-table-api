package com.aberthier.myaccountingtable.models

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.Table
import javax.persistence.Entity
import javax.persistence.OneToMany
import javax.persistence.JoinColumn
import javax.persistence.Id
import javax.persistence.GeneratedValue

@Entity
@Data
@NoArgsConstructor
@Table(name = "sub_account_class")
class SubAccountClass (

        var name: String,
        var description: String,

        @OneToMany
        @JoinColumn(name = "id_sub_account")
        var listFlux: List<MonetaryFlux>,

        @Id
        @GeneratedValue
        var id: Long? = null

)