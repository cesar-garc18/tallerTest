package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {

    private val viewModel = LoginViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {

                val uiState by viewModel.uiState.collectAsState()

                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    LoginForm(
                        result = uiState.result, error = uiState.error
                    ) { username, password ->
                        viewModel.handleSubmit(username = username, password = password)
                    }
                }
            }

        }
    }
}

@Composable
fun LoginForm(
    modifier: Modifier = Modifier,
    result: Boolean,
    error: Boolean,
    onSubmitClick: (String, String) -> Unit
) {
    if (result) {
        Text("login successful")
    } else if (error) {
        Text("login error")
    } else {
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        Column(modifier = modifier) {
            Text("Username")
            TextField(
                value = username,
                onValueChange = {
                    username = it
                },
            )
            Text("Password")
            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
            )
            Button(onClick = {
                onSubmitClick(username, password)
            }) {
                Text("Login")
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MyApplicationTheme {
//        LoginForm(modifier = Modifier)
//    }
//}


