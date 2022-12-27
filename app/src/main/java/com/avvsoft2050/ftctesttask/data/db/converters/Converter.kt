package com.avvsoft2050.ftctesttask.data.db.converters

import androidx.room.TypeConverter
import com.avvsoft2050.ftctesttask.data.model.Bank
import com.avvsoft2050.ftctesttask.data.model.CardNumber
import com.avvsoft2050.ftctesttask.data.model.Country
import com.google.gson.Gson

class Converter {

    private val gson = Gson()

    @TypeConverter
    fun cardNumberToString(cardNumber: CardNumber): String {
        return gson.toJson(cardNumber)
    }

    @TypeConverter
    fun stringToCardNumber(string: String): CardNumber {
        return gson.fromJson(string, CardNumber::class.java)
    }

    @TypeConverter
    fun countryToString(country: Country): String {
        return gson.toJson(country)
    }

    @TypeConverter
    fun stringToCountry(string: String): Country {
        return gson.fromJson(string, Country::class.java)
    }

    @TypeConverter
    fun bankToString(bank: Bank): String {
        return gson.toJson(bank)
    }

    @TypeConverter
    fun stringToBank(string: String): Bank {
        return gson.fromJson(string, Bank::class.java)
    }
}