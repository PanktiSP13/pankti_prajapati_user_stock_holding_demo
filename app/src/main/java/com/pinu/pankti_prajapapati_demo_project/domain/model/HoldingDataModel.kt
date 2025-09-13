package com.pinu.pankti_prajapapati_demo_project.domain.model


import com.google.gson.annotations.SerializedName


data class HoldingsResponse(
    @SerializedName("data") val holdingData: Holdings? = null,
)

data class Holdings(
    @SerializedName("userHolding") val userHolding: List<HoldingDataModel>?,
)


data class HoldingDataModel(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("quantity") val quantity: Int,
    @SerializedName("avgPrice") val avgPrice: Double,
    @SerializedName("ltp") val ltp: Double,
    @SerializedName("close") val close: Double,
) {
    val currentValue: Double
        get() = ltp * quantity

    val investment: Double
        get() = avgPrice * quantity

    val pnl: Double
        get() = currentValue - investment

    val todayPnl: Double
        get() = (close - ltp) * quantity

}


