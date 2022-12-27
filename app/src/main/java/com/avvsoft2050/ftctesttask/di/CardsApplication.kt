package com.avvsoft2050.ftctesttask.di

import android.app.Application
import com.avvsoft2050.ftctesttask.data.CardRepositoryImp
import com.avvsoft2050.ftctesttask.data.db.CardsInfoDatabase

class CardsApplication : Application() {
    private val database by lazy { CardsInfoDatabase.getDatabase(this) }
    val repository by lazy { CardRepositoryImp(database.loadCardDao()) }
}