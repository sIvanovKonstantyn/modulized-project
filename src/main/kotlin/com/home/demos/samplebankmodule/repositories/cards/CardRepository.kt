package com.home.demos.samplebankmodule.repositories.cards

import com.home.demos.samplebankmodule.model.Card
import com.home.demos.samplebankmodule.repositories.Repository

interface CardRepository : Repository {
    fun findAllByClientId(clientId: Long): List<Card>
    fun save(card: Card): Card
}
