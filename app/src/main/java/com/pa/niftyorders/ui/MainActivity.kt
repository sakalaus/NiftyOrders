package com.pa.niftyorders.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import com.pa.niftyorders.ui.NiftyOrdersScreen
import com.pa.niftyorders.ui.theme.NiftyOrdersTheme
import com.pa.niftyorders.utils.WindowSizeClass
import com.pa.niftyorders.utils.rememberWindowSizeClass
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            val windowSizeClass = rememberWindowSizeClass()
            NiftyOrdersScreen(windowSizeClass)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NiftyOrdersTheme() {
        NiftyOrdersScreen(WindowSizeClass.Expanded)
    }
}