package com.pinu.pankti_prajapapati_demo_project.domain.states

import com.pinu.pankti_prajapapati_demo_project.domain.model.HoldingDataModel


data class HoldingsUIState(
    val isLoading: Boolean = false,
    val holdings: List<HoldingDataModel> = emptyList(),
    val error: String? = null,
    val isSummaryExpanded: Boolean = false
) {
    val totalCurrentValue: Double
        get() = holdings.sumOf { it.currentValue }

    val totalInvestment: Double
        get() = holdings.sumOf { it.investment }

    val totalPnl: Double
        get() = totalCurrentValue - totalInvestment

    val todayPnl: Double
        get() = holdings.sumOf { it.todayPnl }

}