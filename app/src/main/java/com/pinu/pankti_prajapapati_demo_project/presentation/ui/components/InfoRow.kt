package com.pinu.pankti_prajapapati_demo_project.presentation.ui.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.pinu.pankti_prajapapati_demo_project.R
import shimmerBrush

@Composable
fun InfoRow(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
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
        
        if(isLoading){
            Box(modifier = Modifier.width(70.dp).height(20.dp).background(shimmerBrush()))
        }else{
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(color = color)
            )
        }
        
    }
}

