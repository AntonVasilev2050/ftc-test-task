package com.avvsoft2050.ftctesttask.data

import com.avvsoft2050.ftctesttask.data.api.ApiFactory
import com.avvsoft2050.ftctesttask.data.db.dao.CardDao
import com.avvsoft2050.ftctesttask.data.model.Card
import com.avvsoft2050.ftctesttask.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow

class CardRepositoryImp(private val cardDao: CardDao) : CardRepository {
    override val allCards: Flow<List<Card>>
        get() = cardDao.getAllCards()

    override suspend fun insert(card: Card) {
        cardDao.insert(card)
    }

    override suspend fun getCard(bin: String): Card? {
        return ApiFactory.apiService.getCard(bin)
    }
}