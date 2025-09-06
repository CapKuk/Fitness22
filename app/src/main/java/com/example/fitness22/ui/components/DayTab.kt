package com.example.fitness22.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DayTab(
    day: Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isCompleted: Boolean = false
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .clickable { onClick() },
        color = when {
            isSelected -> MaterialTheme.colorScheme.primary
            isCompleted -> MaterialTheme.colorScheme.secondary
            else -> MaterialTheme.colorScheme.surfaceVariant
        },
        tonalElevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .width(80.dp)
                .height(40.dp)
                .padding(horizontal = 16.dp, vertical = 5.dp),
            contentAlignment = Alignment.Center
        ) {
            if (isCompleted && !isSelected) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Completed",
                    tint = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier.size(16.dp)
                )
            } else {
                Text(
                    text = "Day $day",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = when {
                        isSelected -> MaterialTheme.colorScheme.onPrimary
                        else -> MaterialTheme.colorScheme.onSurfaceVariant
                    }
                )
            }
        }
    }
}