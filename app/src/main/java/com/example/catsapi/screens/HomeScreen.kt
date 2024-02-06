package com.example.catsapi.screens

import android.webkit.WebSettings.TextSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.catsapi.model.CatFacts

@Composable
fun HomeScreen(fact: MutableState<CatFacts>, makeRequest: () -> Unit) {
    LaunchedEffect(Unit) {
        makeRequest()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround)
    {
        Text("Cat Facts", fontSize = 24.sp)
        Text("Fact: ${fact.value.fact}", fontSize = 18.sp)
        Button(onClick = { makeRequest() }, modifier = Modifier .padding(top = 20.dp)) {
            Text("Surprise me!")
        }
    }
}