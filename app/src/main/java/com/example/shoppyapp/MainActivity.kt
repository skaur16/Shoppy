package com.example.shoppyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.shoppyapp.features.DataStoreUtil
import com.example.shoppyapp.features.ShoppyScreen
import com.example.shoppyapp.features.ShoppyViewModel
import com.example.shoppyapp.ui.theme.ShoppyAppTheme

class MainActivity : ComponentActivity() {

    private  var dataStore: DataStoreUtil = DataStoreUtil.create(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppyAppTheme {
                //display the screen on launch
                ShoppyScreen(
                    viewModel = ShoppyViewModel(),
                    dataStore = dataStore
                )
            }
        }
    }
}

