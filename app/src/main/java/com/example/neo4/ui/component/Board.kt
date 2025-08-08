package com.example.neo4.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neo4.ui.theme.Black
import com.example.neo4.ui.theme.Gray
import com.example.neo4.ui.theme.White

@Composable
fun Board(
    modifier: Modifier = Modifier,
    title: String,
    writer: String,
    onclick: () -> Unit = {}
) {
    Box(
        modifier
            .clickable{ onclick() }
            .fillMaxWidth()
            .background(White)
            .padding(horizontal = 30.dp, vertical = 20.dp)
    ) {
        Column {
            Text(
                text = title,
                fontSize = 16.sp, color = Black,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(
                text = writer,
                fontSize = 11.sp,
                color = Gray,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BoardPreview() {
    Board(title = "제목", writer = "작성자")
}