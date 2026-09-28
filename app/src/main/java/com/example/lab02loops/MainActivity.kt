package com.example.lab02loops

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            CycleLabScreen()
        }
    }
}

@Composable
fun CycleLabScreen() {

    var xText by remember {
        mutableStateOf("")
    }

    var result by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "(x−2)(x−4)(x−8)(x−16)(x−32)(x−64)(x−128)",
            fontSize = 16.sp
        )

        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        )

        Text(
            text = "(x−1)(x−3)(x−7)(x−15)(x−31)(x−63)(x−127)",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Введите x:"
        )

        OutlinedTextField(
            value = xText,
            onValueChange = {
                xText = it
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {

                val x = xText.toDoubleOrNull()

                if (x == null) {
                    result = "Введите корректное число"
                    return@Button
                }

                var numerator = 1.0
                var denominator = 1.0

                for (n in 1..7) {

                    val power = 2.0.pow(n)

                    numerator *= x - power
                    denominator *= x - (power - 1)
                }

                if (denominator == 0.0) {
                    result = "Ошибка: знаменатель равен нулю"
                } else {
                    val answer = numerator / denominator
                    result = answer.toString()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Вычислить")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Результат: $result",
            fontSize = 18.sp
        )
    }
}