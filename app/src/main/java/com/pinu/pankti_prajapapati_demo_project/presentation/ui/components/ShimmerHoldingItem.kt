package com.pinu.pankti_prajapapati_demo_project.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import shimmerBrush

@Composable
fun ShimmerHoldingItem() {
    val brush = shimmerBrush()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.weight(0.7f)) {
                Box(modifier = Modifier
                        .size(width = 100.dp, height = 20.dp)
                        .background(brush)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .size(width = 60.dp, height = 16.dp)
                        .background(brush)
                )
            }
            Column(modifier = Modifier.weight(0.7f), horizontalAlignment = Alignment.End) {
                Box(
                    modifier = Modifier
                        .size(width = 80.dp, height = 16.dp)
                        .background(brush)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .size(width = 80.dp, height = 16.dp)
                        .background(brush)
                )
            }
        }
    }
}
