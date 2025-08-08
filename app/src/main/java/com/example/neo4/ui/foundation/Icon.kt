package com.example.neo4.ui.foundation

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.example.neo4.R

@Composable
fun AddIcon(
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(24.dp, 24.dp),
    tint: Color = Color.Unspecified,
    description: String
) {
    Icon(
        modifier = modifier.size(size),
        painter = painterResource(R.drawable.ic_add),
        contentDescription = description,
        tint = tint
    )
}

@Composable
fun MoreIcon(
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(24.dp, 24.dp),
    tint: Color = Color.Unspecified,
    description: String
) {
    Icon(
        modifier = modifier.size(size),
        painter = painterResource(R.drawable.ic_more),
        contentDescription = description,
        tint = tint
    )
}

@Composable
fun BackIcon(
    modifier: Modifier = Modifier,
    size: DpSize = DpSize(24.dp, 24.dp),
    tint: Color = Color.Unspecified,
    description: String
) {
    Icon(
        modifier = modifier.size(size),
        painter = painterResource(R.drawable.ic_arrow),
        contentDescription = description,
        tint = tint
    )
}