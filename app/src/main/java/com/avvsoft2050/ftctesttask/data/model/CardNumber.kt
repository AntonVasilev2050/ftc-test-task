package com.avvsoft2050.ftctesttask.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class CardNumber(
    @SerializedName("length")
    @Expose
    var length: Int,

    @SerializedName("luhn")
    @Expose
    var luhn: Boolean?
)