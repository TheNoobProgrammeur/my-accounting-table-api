package com.aberthier.myaccountingtable.models

import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
class User (
    var firstname: String,
    var lastname: String,

    @OneToMany
    @JoinColumn(name = "user_id")
    var accountMonth: List<AccountMonth>? = ArrayList<AccountMonth>(),

    @Id
    @GeneratedValue
    var id: Long? = null
)