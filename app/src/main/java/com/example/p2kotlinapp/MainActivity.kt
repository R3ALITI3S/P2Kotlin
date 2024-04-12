package com.example.p2kotlinapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.p2kotlinapp.R.string.tomatoplant
import com.example.p2kotlinapp.ui.theme.P2KotlinAppTheme


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            P2KotlinAppTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()
    ){
        Button(
            modifier = Modifier
                .height(48.dp)
                .width(280.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 4.dp),
            enabled = true,
            onClick = { Log.d("tag","tesy")}
        ) {
            Text(text = (stringResource(id = R.string.waterplant)), modifier = Modifier)
        }

        Button(
            modifier = Modifier
                .height(96.dp)
                .width(360.dp)
                .align(Alignment.TopCenter)
                .padding(bottom = 4.dp),
            enabled = true,
            onClick = { Log.d("tag","tesy")}
        ) {
            Text(text = (stringResource(id = R.string.waterlevel)), modifier = Modifier)
        }
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()


    ) {
        Image(
            painter = painterResource(R.drawable.tomatoplant),
            contentDescription = "tomatoplant",
            modifier = Modifier
                .wrapContentSize()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(tomatoplant), fontSize = 18.sp)
    }
}