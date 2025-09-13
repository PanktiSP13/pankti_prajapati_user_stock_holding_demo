package com.pinu.pankti_prajapapati_demo_project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.pinu.pankti_prajapapati_demo_project.presentation.theme.Pankti_prajapapati_Demo_ProjectTheme
import com.pinu.pankti_prajapapati_demo_project.presentation.ui.screens.HoldingsScreenRootUI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                HoldingsScreenRootUI()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Pankti_prajapapati_Demo_ProjectTheme(darkTheme = false) {
        content()
    }
}