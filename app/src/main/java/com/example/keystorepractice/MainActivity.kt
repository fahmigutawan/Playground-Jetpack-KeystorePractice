package com.example.keystorepractice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.keystorepractice.ui.theme.KeystorePracticeTheme

class MainActivity : ComponentActivity() {
    val crypto = CryptoUtils()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val input = remember {
                mutableStateOf("")
            }
            val encrypted = remember {
                mutableStateOf("")
            }
            val decrypted = remember {
                mutableStateOf("")
            }
            val arr = remember {
                mutableStateOf<ByteArray?>(null)
            }
            KeystorePracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(32.dp)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(24.dp)
                    ) {
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = input.value,
                            onValueChange = { input.value = it },
                            label = {
                                Text(text = "Input")
                            }
                        )

                        OutlinedTextField(
                            modifier = Modifier
                                .height(200.dp)
                                .fillMaxWidth(),
                            value = encrypted.value,
                            onValueChange = {},
                            label = {
                                Text(text = "ENCRYPTED")
                            }
                        )

                        OutlinedTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            value = decrypted.value,
                            onValueChange = {},
                            label = {
                                Text(text = "DECRYPTED")
                            }
                        )

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                encrypted.value = crypto.encrypt(
                                    aliasKey = "ENC",
                                    data = input.value
                                )
                            }
                        ) {
                            Text(text = "ENCRYPT")
                        }

                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                decrypted.value = crypto.decrypt(
                                    "ENC",
                                    encrypted.value
                                )
                            }
                        ) {
                            Text(text = "DECRYPT")
                        }
                    }
                }
            }
        }
    }
}