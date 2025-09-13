package com.pinu.pankti_prajapapati_demo_project.presentation.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.pinu.pankti_prajapapati_demo_project.R
import com.pinu.pankti_prajapapati_demo_project.domain.states.HoldingsUIState
import com.pinu.pankti_prajapapati_demo_project.domain.viewmodels.HoldingsViewModel
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.BgColor
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.ColorPrimary
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.FailureRed
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.SuccessGreen
import com.pinu.pankti_prajapapati_demo_project.presentation.ui.components.HoldingsItem
import com.pinu.pankti_prajapapati_demo_project.presentation.ui.components.InfoRow
import com.pinu.pankti_prajapapati_demo_project.presentation.ui.components.ShimmerHoldingItem
import com.pinu.pankti_prajapapati_demo_project.presentation.ui.utils.AppUtils.modifiedAmount


@Composable
fun HoldingsScreenRootUI() {

    val holdingsViewModel: HoldingsViewModel = hiltViewModel<HoldingsViewModel>()
    val holdingState = holdingsViewModel.uiState.collectAsState().value

    LaunchedEffect(Unit) {
        holdingsViewModel.fetchHoldings()
    }

    HoldingsScreen(holdingState, onToggle = {
        holdingsViewModel.toggleSummary()
    })
}


//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HoldingsScreen(holdingsUIState: HoldingsUIState = HoldingsUIState(), onToggle: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(stringResource(R.string.portfolio), style = TextStyle(color = Color.White))
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = ColorPrimary))
        },
        bottomBar = {
            PortfolioSummary(holdingsUIState, onToggle = { onToggle() })
        }) { contentPadding ->

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            when {
                // No internet
                (holdingsUIState.holdings.isEmpty() && !holdingsUIState.isLoading &&
                        !holdingsUIState.isInternetConnected) -> {
                    Text(text = stringResource(R.string.no_internet_connection))
                }

                // Loading shimmer
                (holdingsUIState.isLoading && holdingsUIState.holdings.isEmpty()) -> {
                    LazyColumn(contentPadding = contentPadding) {
                        items(count = 10) {
                            ShimmerHoldingItem()
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }

                // Show holdings
                else -> {
                    LazyColumn(contentPadding = contentPadding) {
                        items(holdingsUIState.holdings) { item ->
                            HoldingsItem(item)
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PortfolioSummary(
    holdingsUIState: HoldingsUIState,
    onToggle: () -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = BgColor),
        elevation = CardDefaults.cardElevation(6.dp),

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .clickable { onToggle() }) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(
                        animationSpec = tween(
                            durationMillis = 500,
                            easing = LinearOutSlowInEasing
                        )
                    )
            ) {
                if (holdingsUIState.isSummaryExpanded) {
                    Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                        Spacer(modifier = Modifier.height(16.dp))
                        InfoRow(
                            isLoading = holdingsUIState.isLoading,
                            label = stringResource(R.string.current_value_label),
                            value = holdingsUIState.totalCurrentValue.modifiedAmount(),
                            isSummaryExpanded = true
                        )
                        InfoRow(
                            isLoading = holdingsUIState.isLoading,
                            label = stringResource(R.string.total_investment),
                            value = holdingsUIState.totalInvestment.modifiedAmount(),
                            isSummaryExpanded = true
                        )
                        InfoRow(
                            isLoading = holdingsUIState.isLoading,
                            label = stringResource(R.string.today_s_profit_loss),
                            value = holdingsUIState.todayPnl.modifiedAmount(),
                            isSummaryExpanded = true,
                            color = if (holdingsUIState.todayPnl > 0) SuccessGreen else FailureRed,
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        HorizontalDivider()
                    }

                }
            }

            Spacer(modifier = Modifier.height(8.dp))
            InfoRow(
                modifier = Modifier.padding(horizontal = 12.dp),
                isLoading = holdingsUIState.isLoading,
                isDefaultItem = true,
                label = stringResource(R.string.profit_loss),
                value = holdingsUIState.totalPnl.modifiedAmount(),
                color = if (holdingsUIState.totalPnl > 0) SuccessGreen else FailureRed,
                isSummaryExpanded = holdingsUIState.isSummaryExpanded,
                onToggle = onToggle
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}




