package com.example.shoppyapp.features.comp

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.shoppyapp.R
import com.example.shoppyapp.features.ShoppyViewModel

@Composable
fun AutoCompleteProvinceTextField(
    selectedProvince: String,
    onProvinceSelected: (String) -> Unit,
    viewModel: ShoppyViewModel,
    modifier: Modifier = Modifier
) {


    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = viewModel.provinceText.value,
            onValueChange = {
                viewModel.provinceText.value = it

                viewModel.suggestProvinces(viewModel.provinceText.value)

                if (viewModel.suggestProvinceList.isNotEmpty()) {
                    viewModel.provinceExpanded.value = true
                }
            },
            label = { Text(text = stringResource(id = R.string.province)) },
        )


        DropdownMenu(
            expanded = viewModel.provinceExpanded.value,
            onDismissRequest = { viewModel.provinceExpanded.value = false }
        ) {
            viewModel.suggestProvinceList.forEach { province ->
                DropdownMenuItem(
                    text = { Text(text = province) },
                    onClick = {
                        viewModel.provinceExpanded.value = false
                        viewModel.provinceText.value = province
                        onProvinceSelected(province)
                    }
                )
            }

        }
    }
}