package com.example.moneycounter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.moneycounter.ui.theme.MoneyCounterTheme
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MoneyCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { MyApp() }
            }
        }
    }
}

@Composable
fun MyApp() {
    var amount by remember { mutableIntStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF546E7A)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "$ $amount",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
            )
            Spacer(modifier = Modifier.height(130.dp))
            CreateCircle(amount) { newAmount -> amount = newAmount }
            Spacer(modifier = Modifier.height(50.dp))
            if(amount >= 10) Text(text= stringResource(id = R.string.reached_10), color = Color.White)
        }
    }
}

@Composable
fun CreateCircle(
    amount: Int,
    updatedAmount: (Int) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(3.dp)
            .size(105.dp)
            .clip(CircleShape) //使用 clip 函数剪裁 波纹效果形状
            .clickable {
                updatedAmount(amount + 1)
                Log.d("amount", "CreateCircle: $amount")
            },
        shape = CircleShape,
        elevation = CardDefaults.cardElevation(5.dp)
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Text(text = stringResource(id = R.string.tap))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MoneyCounterTheme {
        MyApp()
    }
}