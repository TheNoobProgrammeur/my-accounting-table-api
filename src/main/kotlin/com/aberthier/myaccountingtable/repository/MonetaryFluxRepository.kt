package com.aberthier.myaccountingtable.repository

import com.aberthier.myaccountingtable.models.MonetaryFlux
import org.springframework.data.repository.CrudRepository

interface MonetaryFluxRepository: CrudRepository<MonetaryFlux, Long>