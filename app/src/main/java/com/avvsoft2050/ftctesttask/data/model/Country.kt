package com.avvsoft2050.ftctesttask.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class Country(
    @SerializedName("numeric")
    @Expose
    var numeric: String,

    @SerializedName("alpha2")
    @Expose
    var alpha2: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("emoji")
    @Expose
    var emoji: String,

    @SerializedName("currency")
    @Expose
    var currency: String,

    @SerializedName("latitude")
    @Expose
    var latitude: Int,

    @SerializedName("longitude")
    @Expose
    var longitude: Int
)