package com.avvsoft2050.ftctesttask.domain.repository

import androidx.annotation.WorkerThread
import com.avvsoft2050.ftctesttask.data.model.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    val allCards: Flow<List<Card>>

    @WorkerThread
    suspend fun insert(card: Card)
    suspend fun getCard(bin: String): Card?
}