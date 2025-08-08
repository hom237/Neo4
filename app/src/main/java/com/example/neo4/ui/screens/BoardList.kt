package com.example.neo4.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neo4.server.RetrofitBuilder.getBoardService
import com.example.neo4.server.request.RequestBoard
import com.example.neo4.ui.Navigate
import com.example.neo4.ui.component.Board
import com.example.neo4.ui.foundation.AddIcon
import com.example.neo4.ui.theme.Background
import com.example.neo4.ui.theme.Black
import com.example.neo4.ui.theme.Purple
import com.example.neo4.ui.theme.White
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

@Composable
fun BoardListScreen(onMove: (String, id: Int?) -> Unit) {
    var boardList by remember { mutableStateOf(listOf<RequestBoard>()) }

    SideEffect {
        MainScope().launch {
            runCatching {
                getBoardService().getBoardListBySuspend()
            }.onSuccess { result ->
                boardList = result
            }.onFailure { error ->
                error.printStackTrace()
            }
        }
    }

    Box(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
    ) {
        Column {
            // topbar
            Box(
                modifier = Modifier
                    .background(White)
                    .fillMaxWidth()
                    .padding(vertical = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    modifier = Modifier,
                    text = "Neo4의 게시판",
                    color = Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.padding(top = 5.dp))
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(5.dp),
            ) {
                items(boardList.size) { index ->
                    val data = boardList[index]
                    Board(
                        title = data.title,
                        writer = data.name
                    ) {
                        onMove(Navigate.READ.name, data.id)
                    }
                }
            }
        }
        FloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    vertical = 40.dp,
                    horizontal = 30.dp,
                ),
            shape = CircleShape,
            containerColor = Purple,
            onClick = { onMove(Navigate.CREATE.name, null) }
        ) {
            AddIcon(
                modifier = Modifier
                    .padding(5.dp),
                description = "add", tint = White
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun BoardListScreenPreview() {
    BoardListScreen() { _, _ -> }
}