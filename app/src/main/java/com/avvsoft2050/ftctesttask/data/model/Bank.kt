package com.avvsoft2050.ftctesttask.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Bank(
    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("url")
    @Expose
    var url: String,

    @SerializedName("phone")
    @Expose
    var phone: String,

    @SerializedName("city")
    @Expose
    var city: String
)