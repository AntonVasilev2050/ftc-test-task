package com.avvsoft2050.ftctesttask.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.avvsoft2050.ftctesttask.data.model.Card
import com.avvsoft2050.ftctesttask.domain.repository.CardRepository
import kotlinx.coroutines.*

class CardsViewModel(
    private val repository: CardRepository
) : ViewModel() {

    private var card: Card? = null
    private val _cardLiveData = MutableLiveData(card)
    val cardLiveData: LiveData<Card?> get() = _cardLiveData
    private var infoMessage = "Hi there!"
    private val _infoMessageLiveData = MutableLiveData(infoMessage)
    val infoMessageLiveData: LiveData<String> get() = _infoMessageLiveData

    suspend fun onSearchBinInfoButtonClicked(bin: String) {
        infoMessage = ""
        val result = viewModelScope.launch {
            try {
                card = repository.getCard(bin)
                infoMessage = "Data loaded"
                card?.bin = bin
                insert(card)
            } catch (e: Exception) {
                infoMessage = e.message.toString()
                Log.d("TEST_OF_LOADING_DATA", "Error: ${e.message.toString()}")
            }
        }
        result.join()
        if (_cardLiveData.value != card) {
            _cardLiveData.value = card
        }
        _infoMessageLiveData.value = infoMessage
    }

    val allBins: LiveData<List<Card>> = repository.allCards.asLiveData()

    fun insert(card: Card?) = viewModelScope.launch {
        card?.let { repository.insert(it) }
    }

    class CardsViewModelFactory(
        private val repository: CardRepository
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CardsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return CardsViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}