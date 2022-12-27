package com.avvsoft2050.ftctesttask.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.avvsoft2050.ftctesttask.data.db.converters.Converter
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@TypeConverters(Converter::class)
@Entity(tableName = "cards_table")
data class Card(

    @PrimaryKey
    var bin: String,

    @SerializedName("number")
    @Expose
    var cardNumber: CardNumber?,

    @SerializedName("scheme")
    @Expose
    var scheme: String?,

    @SerializedName("type")
    @Expose
    var type: String?,

    @SerializedName("brand")
    @Expose
    var brand: String?,

    @SerializedName("prepaid")
    @Expose
    var prepaid: Boolean?,

    @SerializedName("country")
    @Expose
    var country: Country?,

    @SerializedName("bank")
    @Expose
    var bank: Bank?
)