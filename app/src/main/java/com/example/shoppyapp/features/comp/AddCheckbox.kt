package com.example.shoppyapp.features.comp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shoppyapp.R
import com.example.shoppyapp.features.ShoppyViewModel

@Composable
fun AddCheckBox(
    modifier: Modifier = Modifier,
    viewModel: ShoppyViewModel,
) {
    Card (
        Modifier.fillMaxWidth().padding(horizontal = 80.dp, vertical = 10.dp)
    ){


        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(text = stringResource(id = R.string.addOns))
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = viewModel.ssdChecked.value,
                    onCheckedChange = {
                        viewModel.ssdChecked.value = it
                    }
                )
                Text(text = stringResource(id = R.string.addSSD))
            }
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = viewModel.printerChecked.value,
                    onCheckedChange = {
                        viewModel.printerChecked.value = it
                    }
                )
                Text(text = stringResource(id = R.string.addPrinter))
            }

        }
    }
}