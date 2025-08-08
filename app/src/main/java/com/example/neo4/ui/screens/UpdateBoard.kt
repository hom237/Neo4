package com.example.neo4.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.neo4.server.RetrofitBuilder.getBoardService
import com.example.neo4.server.request.RequestBoard
import com.example.neo4.ui.Navigate
import com.example.neo4.ui.foundation.BackIcon
import com.example.neo4.ui.theme.Gray
import com.example.neo4.ui.theme.Purple
import com.example.neo4.ui.theme.White
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

@Composable
fun UpdateBoardScreen(
    id: Int,
    onMove: (String) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var board by remember { mutableStateOf<RequestBoard>(RequestBoard(0, "", "", "")) }
    val context = LocalContext.current

    DisposableEffect(Unit) {
        val scope = CoroutineScope(Dispatchers.Default)
        scope.launch {
            runCatching {
                getBoardService().getBoardById(id = id)
            }.onSuccess { result ->
                board = result
                title = result.title
                content = result.content
            }.onFailure { error ->
                board = RequestBoard(0, "값을 못 불러왔습니다.", "", "")
                error.printStackTrace()
            }
        }

        onDispose {
            scope.cancel()
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Box(
                modifier = Modifier
                    .background(White)
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "게시글 생성",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = {
                        onMove(Navigate.READ.name)
                    }
                ) {
                    BackIcon(description = "뒤로가기")
                }
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "제목") },
                singleLine = true
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                value = content,
                onValueChange = { content = it },
                label = { Text(text = "내용") },
                singleLine = true
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 30.dp, vertical = 40.dp),
            onClick = {
                val updatedBoard = RequestBoard(
                    id = board.id,
                    title = title,
                    content = content,
                    name = board.name
                )
                MainScope().launch {
                    runCatching {
                        getBoardService().updateBoard(updatedBoard)
                    }.onSuccess { _ ->
                        onMove(Navigate.READ.name)
                    }.onFailure { error ->
                        Toast.makeText(context, "게시글 갱신에 실패했습니다.", Toast.LENGTH_SHORT).show()
                        error.printStackTrace()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple,
                contentColor = White,
                disabledContainerColor = Gray,
                disabledContentColor = White
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Text(
                modifier = Modifier,
                text = "게시글 갱신",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun UpdateBoardScreenPreview() {
    UpdateBoardScreen(id = 1) { }
}