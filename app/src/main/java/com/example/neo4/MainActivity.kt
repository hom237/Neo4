package com.example.neo4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.neo4.ui.New4App
import com.example.neo4.ui.theme.Neo4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Neo4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    New4App(innerPadding)
                }
            }
        }
    }
}