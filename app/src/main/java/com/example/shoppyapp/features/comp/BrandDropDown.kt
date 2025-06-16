package com.example.shoppyapp.features.comp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.shoppyapp.R
import com.example.shoppyapp.features.Brand
import com.example.shoppyapp.features.ShoppyViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandDropDown(modifier: Modifier = Modifier,
                  viewModel: ShoppyViewModel,
                  options: List<Brand>
                  )
{

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        ExposedDropdownMenuBox(
            expanded = viewModel.brandExpanded.value,
            onExpandedChange = {
                viewModel.brandExpanded.value = !viewModel.brandExpanded.value
            }
        ) {
            TextField(
                value = viewModel.brandSelected.value.name,
                onValueChange = {},
                label = { Text(text = stringResource(id = R.string.selectBrand)) },
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = viewModel.brandExpanded.value
                    )
                },
                modifier = Modifier.menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = viewModel.brandExpanded.value,
                onDismissRequest = { viewModel.brandExpanded.value = false }
            ) {
                options.forEach {brand->
                    DropdownMenuItem(
                        text = { Text(text = brand.name) },
                        onClick = {
                            viewModel.brandExpanded.value = false
                            viewModel.brandSelected.value = brand
                        }
                    )

                }
            }
        }
    }
}