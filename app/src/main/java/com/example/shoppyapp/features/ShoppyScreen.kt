package com.example.shoppyapp.features

import android.util.Log
import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import com.example.shoppyapp.R
import com.example.shoppyapp.features.comp.AddCheckBox
import com.example.shoppyapp.features.comp.AddPeriPherals
import com.example.shoppyapp.features.comp.AutoCompleteProvinceTextField
import com.example.shoppyapp.features.comp.BrandDropDown
import com.example.shoppyapp.features.comp.ComputerRadioGroup

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppyScreen(
    modifier: Modifier = Modifier,
    viewModel: ShoppyViewModel,
    dataStore: DataStoreUtil
) {

    //executes on each recomposition
    LaunchedEffect(key1 = Unit){
        if(viewModel.computerTypeSelected.value == ComputerType.Desktop){
            viewModel.peripheralsList = viewModel.desktopPeripherals
        }
        else{
            viewModel.peripheralsList = viewModel.laptopPeripherals
        }

    }
    Scaffold(
        Modifier.fillMaxSize(),
        topBar = {
            //displays heading
            TopAppBar(title = { Text(text = stringResource(id = R.string.topBarName)) })
        }
    ) {
        Column(
            Modifier.padding(it).verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            //Title centered
                Text(text = stringResource(id = R.string.titleName),
                    style = MaterialTheme.typography.headlineLarge
                    )

            //Username textfield
                TextField(
                    value = viewModel.userName.value,
                    onValueChange = {
                        viewModel.userName.value = it
                    },
                    label = { Text(text = stringResource(id = R.string.userNameLabel)) }

                )

            //autocomplete textfield for province
                AutoCompleteProvinceTextField(
                    selectedProvince = viewModel.selectedProvince.value,
                    onProvinceSelected = {
                        viewModel.selectedProvince.value = it
                    },
                    viewModel = viewModel
                )

            //radio group for computer type
                ComputerRadioGroup(
                    viewModel = viewModel,
                    options = ComputerType.entries.toList()
                )

            //dropdown to choose brands
                BrandDropDown(
                    viewModel = viewModel,
                    options = Brand.entries.toList()
                )

            //optional add-ons
                AddCheckBox(viewModel = viewModel)

            //peripheral radiogroup based on computer type
               AddPeriPherals(
                   viewModel = viewModel,
                   peripherals = viewModel.selectPeripheral(viewModel.computerTypeSelected.value.name)
               )

            //calculate button
                Button(
                    onClick = {
                        viewModel.updateInvoice(dataStore)

                    }
                ){
                    Text(text = stringResource(id = R.string.calculate))
                }

            //invoice display
                TextField(
                    value = viewModel.invoice.value,
                    onValueChange = {},
                    readOnly = true,
                    label = {Text(text =  stringResource(id = R.string.invoice)) }
                )
        }



    }
}