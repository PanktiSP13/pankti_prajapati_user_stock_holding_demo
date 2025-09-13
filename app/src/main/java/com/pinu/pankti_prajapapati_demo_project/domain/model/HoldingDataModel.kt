package com.pinu.pankti_prajapapati_demo_project.domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HoldingsResponse(
    @SerialName("data") val holdingData: Holdings? = null,
)

@Serializable
data class Holdings(
    @SerialName("userHolding") val userHolding: List<HoldingDataModel>?,
)


@Serializable
data class HoldingDataModel(
    @SerialName("symbol") val symbol: String,
    @SerialName("quantity") val quantity: Int,
    @SerialName("avgPrice") val avgPrice: Double,
    @SerialName("ltp") val ltp: Double,
    @SerialName("close") val close: Double,
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


