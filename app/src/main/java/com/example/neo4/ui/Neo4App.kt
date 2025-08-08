package com.example.neo4.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.neo4.ui.screens.BoardListScreen
import com.example.neo4.ui.screens.CreateBoardScreen
import com.example.neo4.ui.screens.ShowBoardScreen
import com.example.neo4.ui.screens.UpdateBoardScreen

enum class Navigate() {
    BOARD_LIST, READ, CREATE, UPDATE
}

@Composable
fun New4App(innerPadding: PaddingValues) {
    val nevController = rememberNavController()
    var id by remember { mutableIntStateOf(0) }

    Box(modifier = Modifier.padding(innerPadding)) {
        NavHost(
            navController = nevController,
            startDestination = Navigate.BOARD_LIST.name
        ) {
            composable(route = Navigate.BOARD_LIST.name) {
                BoardListScreen() { destination, inputId ->
                    if(inputId != null){
                        id = inputId
                    }
                    nevController.navigate(destination)
                }
            }
            composable(route = Navigate.CREATE.name) {
                CreateBoardScreen() {
                    nevController.navigate(it)
                }
            }
            composable(route = Navigate.READ.name) {
                ShowBoardScreen(id = id) {
                    nevController.navigate(it)
                }
            }
            composable(route = Navigate.UPDATE.name) {
                UpdateBoardScreen(id = id) {
                    nevController.navigate(it)
                }
            }
        }
    }
}