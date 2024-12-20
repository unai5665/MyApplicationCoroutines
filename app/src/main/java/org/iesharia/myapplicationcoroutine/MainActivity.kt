package org.iesharia.myapplicationcoroutine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoroutineExampleScreen()
        }
    }
}

@Composable
fun CoroutineExampleScreen() {
    val counter = remember { mutableStateOf(0) }
    val isLoading = remember { mutableStateOf(false) }


    LaunchedEffect(isLoading.value) {
        if (isLoading.value) {
            delay(3000)
            counter.value += 1
            isLoading.value = false
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Contador: ${counter.value}")
        Button(
            onClick = {
                isLoading.value = true
                counter.value = 0
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Iniciar tarea")
        }

        if (isLoading.value) {
            Text(text = "Cargando...", modifier = Modifier.padding(top = 16.dp))
        }
    }
}
