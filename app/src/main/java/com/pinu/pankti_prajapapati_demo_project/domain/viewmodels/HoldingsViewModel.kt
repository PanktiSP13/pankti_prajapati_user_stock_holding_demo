package com.pinu.pankti_prajapapati_demo_project.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pinu.pankti_prajapapati_demo_project.data.network.NoInternetException
import com.pinu.pankti_prajapapati_demo_project.domain.repository.HoldingsRepository
import com.pinu.pankti_prajapapati_demo_project.domain.states.HoldingsUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HoldingsViewModel @Inject constructor(private val holdingsRepository: HoldingsRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(HoldingsUIState())
    val uiState: StateFlow<HoldingsUIState> = _uiState.asStateFlow()


    fun fetchHoldings() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update { it.copy(isLoading = true) }
            holdingsRepository.fetchHoldings().collectLatest { result ->
                result.fold(
                    onSuccess = { data ->
                        _uiState.update { it.copy(isLoading = false,
                            error = null,
                            holdings = (data.holdingData?.userHolding ?: emptyList())) }
                    },
                    onFailure = { error ->
                        when (error) {
                            is NoInternetException -> {
                                _uiState.update { it.copy(isLoading = false, isInternetConnected = false,
                                    error = error.message) }
                            }

                            else -> {
                                _uiState.update { it.copy(isLoading = false, error = error.toString()) }
                            }
                        }

                    })

            }
        }
    }

    fun toggleSummary() {
        _uiState.update { it.copy(isSummaryExpanded = !it.isSummaryExpanded) }
    }
}