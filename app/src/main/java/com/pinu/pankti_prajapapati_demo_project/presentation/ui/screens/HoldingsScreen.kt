package com.pinu.pankti_prajapapati_demo_project.presentation.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.pinu.pankti_prajapapati_demo_project.R
import com.pinu.pankti_prajapapati_demo_project.domain.model.HoldingDataModel
import com.pinu.pankti_prajapapati_demo_project.domain.states.HoldingsUIState
import com.pinu.pankti_prajapapati_demo_project.domain.viewmodels.HoldingsViewModel
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.BgColor
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.ColorPrimary
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.FailureRed
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.SuccessGreen
import com.pinu.pankti_prajapapati_demo_project.presentation.ui.utils.AppUtils.modifiedAmount
import com.pinu.pankti_prajapapati_demo_project.presentation.ui.utils.dummyHolding
import com.pinu.pankti_prajapapati_demo_project.presentation.ui.utils.dummyHoldingsList

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
                Text("Holdings", style = TextStyle(color = Color.White))
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = ColorPrimary))
        },
        bottomBar = {
            PortfolioSummary(holdingsUIState, onToggle = { onToggle() })
        }) { contentPadding ->
        LazyColumn(contentPadding = contentPadding) {
            items(holdingsUIState.holdings) { item ->
                HoldingsItem(item) {

                }
            }

        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PortfolioSummary(
    holdingsUIState: HoldingsUIState = HoldingsUIState(holdings = dummyHoldingsList),
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
                            label = stringResource(R.string.current_value_label),
                            value = holdingsUIState.totalCurrentValue.modifiedAmount(),
                            isSummaryExpanded = holdingsUIState.isSummaryExpanded
                        )
                        InfoRow(
                            label = stringResource(R.string.total_investment),
                            value = holdingsUIState.totalInvestment.modifiedAmount(),
                            isSummaryExpanded = holdingsUIState.isSummaryExpanded
                        )
                        InfoRow(
                            label = stringResource(R.string.today_s_profit_loss),
                            value = holdingsUIState.todayPnl.modifiedAmount(),
                            isSummaryExpanded = holdingsUIState.isSummaryExpanded,
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

@Composable
fun InfoRow(
    modifier: Modifier = Modifier,
    label: String, value: String,
    color: Color = Color.Black,
    isSummaryExpanded: Boolean,
    isDefaultItem: Boolean = false,
    onToggle: () -> Unit = {},
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                if (isDefaultItem) {
                    onToggle()
                }
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge
            )
            AnimatedVisibility(isDefaultItem) {
                Icon(
                    imageVector = if (isSummaryExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isSummaryExpanded) stringResource(R.string.expanded) else stringResource(R.string.collapsed),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .size(24.dp)
                        .alignByBaseline(),
                )
            }
        }
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(color = color)
        )
    }
}


@Composable
fun HoldingsItem(item: HoldingDataModel = dummyHolding, onItemClick: () -> Unit = {}) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .clickable {
                onItemClick()
            }) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(0.7f)) {
                Text(item.symbol, style = TextStyle(color = Color.Black, fontSize = 16.sp, fontWeight = FontWeight.SemiBold))
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(stringResource(R.string.net_qty), style = TextStyle(color = Color.Gray))
                    Text(item.quantity.toString(), style = TextStyle(color = Color.Black))
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Column(modifier = Modifier.weight(0.7f)) {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Text(stringResource(R.string.ltp_label), style = TextStyle(color = Color.Gray))
                    Text(item.ltp.modifiedAmount(), style = TextStyle(color = Color.Black))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(stringResource(R.string.pnl_label), style = TextStyle(color = Color.Gray))
                    Text(item.pnl.modifiedAmount(), style = TextStyle(color = if (item.pnl > 0) SuccessGreen else FailureRed))
                }
            }

        }
        HorizontalDivider()
    }
}



