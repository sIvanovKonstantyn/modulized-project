package com.home.demos.samplebankmodule.repositories.payments

import com.home.demos.samplebankmodule.model.Payment
import com.home.demos.samplebankmodule.repositories.Repository

sealed interface PaymentRepository : Repository {
    fun findAllByClientId(clientId: Long): List<Payment>
    fun save(payment: Payment): Payment
}
