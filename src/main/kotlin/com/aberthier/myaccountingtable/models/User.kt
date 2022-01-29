package com.aberthier.myaccountingtable.models

import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.Cascade
import org.hibernate.annotations.CascadeType
import java.time.YearMonth
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.OneToMany
import javax.persistence.Table


@Entity
@Data
@NoArgsConstructor
@Table(name = "app_user")
data class User(
    var firstname: String,
    var lastname: String,
    @OneToMany
    @JoinColumn(name = "user_id")
    @Cascade(CascadeType.DELETE)
    var accountMonth: MutableMap<YearMonth, AccountMonth>? = HashMap<YearMonth, AccountMonth>(),
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
)