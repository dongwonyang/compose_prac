package com.example.compose_prac

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_prac.data.GeocodeResponse
import com.example.compose_prac.data.GeocodeResult
import com.example.compose_prac.data.LocationData
import com.example.compose_prac.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationViewModel : ViewModel() {
    private val _location = mutableStateOf<LocationData?>(null)
    val location: State<LocationData?> = _location

    private val _address = mutableStateOf<List<String>>(emptyList())
    val address: State<List<String>> = _address

    private val _shoppingAddItem =  MutableStateFlow(ShoppingItem())
    val shoppingAddItem = _shoppingAddItem.asStateFlow()

    private val service = RetrofitClient.getCodingApiService

    fun updateLocation(newLocationData: LocationData) {
        _location.value = newLocationData
    }


    fun fetchAddress(latlng: String) = viewModelScope.launch {
        try {
            service.getAddressFromCoordinates(latlng = latlng).let {
                _shoppingAddItem.update { prev ->
                    prev.copy(
                        address = it.results.map {
                            it.formattedAddress
                        }.toString()
                    )
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updateShoppingItemName(newName: String) {
        _shoppingAddItem.value = _shoppingAddItem.value.copy(name = newName)
    }

    fun updateShoppingItemQuantity(newQuantity: String) {
        _shoppingAddItem.value = _shoppingAddItem.value.copy(quantity = newQuantity)
    }

    fun resetShoppingAddItem() {
        _shoppingAddItem.value = ShoppingItem()
    }
}