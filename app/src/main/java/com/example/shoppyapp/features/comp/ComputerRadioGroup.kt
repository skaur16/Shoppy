package com.example.shoppyapp.features.comp

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.shoppyapp.R
import com.example.shoppyapp.features.ComputerType
import com.example.shoppyapp.features.ShoppyViewModel

@Composable
fun ComputerRadioGroup(
    modifier: Modifier = Modifier,
    viewModel: ShoppyViewModel,
    options: List<ComputerType>
) {

    Card (
        Modifier.fillMaxWidth().padding(horizontal = 80.dp, vertical = 10.dp)
    ){


        Column(
            horizontalAlignment = Alignment.Start
        ) {

            Text(
                text = stringResource(id = R.string.computerType),
                style = MaterialTheme.typography.bodyMedium
            )

            options.forEach { option ->

                Row() {
                    RadioButton(
                        selected = (option == viewModel.computerTypeSelected.value),
                        onClick = {
                            viewModel.computerTypeSelected.value = option

                        }
                    )
                    Text(
                        text = option.name,
                        modifier = Modifier
                            .clickable {
                                viewModel.computerTypeSelected.value = option
                            }
                            .padding(16.dp)
                    )

                }
            }
        }
    }
}