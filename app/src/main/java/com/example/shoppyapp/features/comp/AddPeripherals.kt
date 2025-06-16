package com.example.shoppyapp.features.comp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shoppyapp.R
import com.example.shoppyapp.features.ShoppyViewModel

@Composable
fun AddPeriPherals(modifier: Modifier = Modifier,
                   viewModel: ShoppyViewModel,
                   peripherals: List<String>

) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 80.dp, vertical = 10.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ){
            Text(text = stringResource(id = R.string.addPeripherals),
                style = MaterialTheme.typography.bodyMedium
                )
            peripherals.forEach { peripheral ->
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (peripheral == viewModel.selectedPeripheral.value),
                        onClick = {
                            viewModel.selectedPeripheral.value = peripheral
                        }
                    )
                    Text(text = peripheral)
                }
            }




        }
    }

}