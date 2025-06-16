package com.example.shoppyapp.features

import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.datastore.dataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.shoppyapp.R
import kotlinx.coroutines.launch

class ShoppyViewModel : ViewModel() {


    var userName = mutableStateOf<String>("")

    var selectedProvince = mutableStateOf("")

    var provinceText = mutableStateOf("")

    var provinceExpanded = mutableStateOf(false)

    var suggestProvinceList = mutableStateListOf<String>()

    var computerTypeSelected = mutableStateOf(ComputerType.Desktop)

    var brandExpanded = mutableStateOf(false)

    var brandSelected = mutableStateOf(Brand.Hp)

    var ssdChecked = mutableStateOf(false)

    var printerChecked = mutableStateOf(false)


    var peripheralsList = mutableStateListOf<String>()

    var laptopPeripherals = mutableStateListOf<String>("Cooling Mat", "USB C- Hub", "Laptop Stand")

    var desktopPeripherals = mutableStateListOf<String>("None", "Webcam", "External Hard Drive")

    var selectedPeripheral = mutableStateOf("")

    var totalCost = mutableDoubleStateOf(0.0)

    var invoice = mutableStateOf("")


    fun suggestProvinces(province: String) {
        val provincesList = listOf(
            "Ontario", "Quebec", "Alberta", "British Columbia",
            "Manitoba", "Nova Scotia", "New Brunswick", "Newfoundland and Labrador"
        )
        suggestProvinceList.clear()

        if (province.length < 2) {
            return
        }

        for (item in provincesList) {

            if (item.take(2).toLowerCase() == province.take(2).toLowerCase()) {
                if (!suggestProvinceList.contains(item)) {

                    suggestProvinceList.add(item)
                }
            }
        }
    }

    fun selectPeripheral(type: String): List<String> {
        if (type == ComputerType.Desktop.name) {
            return mutableStateListOf("None", "Webcam", "External Hard Drive")
        }
        else  {
            return mutableStateListOf("Cooling Mat", "USB C- Hub", "Laptop Stand")
        }
    }

    fun additionalValue() : String{
        if(ssdChecked.value){
            return "SSD"
        }
        if(printerChecked.value) {
            return "Printer"
        }
        if(ssdChecked.value && printerChecked.value){
            return "SSD and Printer"
        }
        return "None"
    }


    fun calculate() : Double {
        //cost for desktop brands
        if(computerTypeSelected.value == ComputerType.Desktop){
            if(brandSelected.value == Brand.Hp){
                totalCost.doubleValue += 400
            }
            if(brandSelected.value == Brand.Dell){
                totalCost.doubleValue += 475
            }
            if(brandSelected.value == Brand.Lenovo){
                totalCost.doubleValue += 450
            }
        }

        //cost of laptop brands
        if(computerTypeSelected.value == ComputerType.Laptop){
            if(brandSelected.value == Brand.Hp){
                totalCost.doubleValue += 1150
            }
            if(brandSelected.value == Brand.Dell){
                totalCost.doubleValue += 1249
            }
            if(brandSelected.value == Brand.Lenovo){
                totalCost.doubleValue += 1549
            }
        }

        //cost for add-ons
        if(ssdChecked.value){
            totalCost.doubleValue += 60
        }

        if(printerChecked.value){
            totalCost.doubleValue += 100
        }

        //cost for added peripherals
        if(selectedPeripheral.value == "Webcam"){
            totalCost.doubleValue += 95
        }

        if(selectedPeripheral.value == "External Hard Drive"){
            totalCost.doubleValue += 64
        }
        if(selectedPeripheral.value == "Cooling Mat"){
            totalCost.doubleValue += 33
        }
        if(selectedPeripheral.value == "USB C- Hub"){
            totalCost.doubleValue += 60
        }
        if(selectedPeripheral.value == "Laptop Stand"){
            totalCost.doubleValue += 139
        }

        totalCost.doubleValue += 0.13 * totalCost.doubleValue
        totalCost.doubleValue = String.format("%.2f", totalCost.doubleValue).toDouble()

        return totalCost.doubleValue

    }

    fun updateInvoice(dataStore: DataStoreUtil) {
        val str =
            "Customer : ${userName.value} \n" +
                    "Province : ${selectedProvince.value}\n" +
                    "Computer : ${computerTypeSelected.value}\n" +
                    "Brand : ${brandSelected.value}\n" +
                    "Additional : ${additionalValue()}\n" +
                    "Added Peripherals : ${selectedPeripheral.value}\n" +
                    "Cost : $ ${calculate()}"


        viewModelScope.launch {
            dataStore.clear()
            dataStore.setData("invoice", str)
            invoice.value = dataStore.getData<String>("invoice").toString()
        }

    }

}