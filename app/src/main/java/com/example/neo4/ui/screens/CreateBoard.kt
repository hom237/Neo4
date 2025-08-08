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
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

@Composable
fun CreateBoardScreen(onMove: (String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }
    var writer by remember { mutableStateOf("") }
    val context = LocalContext.current

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
                        onMove(Navigate.BOARD_LIST.name)
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

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp),
                value = writer,
                onValueChange = { writer = it },
                label = { Text(text = "작성자") },
                singleLine = true
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .padding(horizontal = 30.dp, vertical = 40.dp),
            onClick = {
                MainScope().launch {
                    val requestBody = RequestBoard(
                        id = 0,
                        title = title,
                        content = content,
                        name = writer
                    )
                    runCatching {
                        getBoardService().createBoard(requestBody)
                    }.onSuccess {
                        onMove(Navigate.BOARD_LIST.name)
                    }.onFailure {
                        Toast.makeText(context, "게시글 등록 실패", Toast.LENGTH_SHORT).show()
                        it.printStackTrace()
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
                text = "게시글 등록",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = White
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CreateBoardScreenPreview() {
    CreateBoardScreen() {

    }
}