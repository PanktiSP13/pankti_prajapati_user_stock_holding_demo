package com.pinu.pankti_prajapapati_demo_project.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinu.pankti_prajapapati_demo_project.domain.repository.HoldingsRepository
import com.pinu.pankti_prajapapati_demo_project.domain.states.HoldingsUIState
import com.pinu.pankti_prajapapati_demo_project.presentation.ui.utils.dummyHoldingsList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoldingsViewModel @Inject constructor(private val holdingsRepository: HoldingsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HoldingsUIState())
    val uiState: StateFlow<HoldingsUIState> = _uiState.asStateFlow()


    fun fetchHoldings() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            holdingsRepository.fetchHoldings().collect { result ->
                result.fold(
                    onSuccess = { data ->
                        _uiState.update { it.copy(isLoading = false, holdings = dummyHoldingsList ?:(data.holdingData?.userHolding ?: emptyList())) }
                    },
                    onFailure = { error ->
                        _uiState.update { it.copy(isLoading = false, error = error.toString()) }
                    })

            }
        }
    }

    fun toggleSummary() {
        _uiState.update { it.copy(isSummaryExpanded = !it.isSummaryExpanded) }
    }
}