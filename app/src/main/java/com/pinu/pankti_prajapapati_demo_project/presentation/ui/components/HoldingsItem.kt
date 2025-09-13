package com.pinu.pankti_prajapapati_demo_project.presentation.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pinu.pankti_prajapapati_demo_project.R
import com.pinu.pankti_prajapapati_demo_project.domain.model.HoldingDataModel
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.FailureRed
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.SuccessGreen
import com.pinu.pankti_prajapapati_demo_project.presentation.ui.utils.AppUtils.modifiedAmount

@Composable
fun HoldingsItem(item: HoldingDataModel, onItemClick: () -> Unit = {}) {

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

