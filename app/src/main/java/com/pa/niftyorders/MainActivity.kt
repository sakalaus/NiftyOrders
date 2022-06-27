package com.pa.niftyorders

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.pa.niftyorders.ui.NiftyOrdersApp
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NiftyOrdersApp()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NiftyOrdersTheme() {

    }
}