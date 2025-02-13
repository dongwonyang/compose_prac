package com.example.compose_prac.ui.screen.add

import androidx.lifecycle.ViewModel
import com.example.compose_prac.ui.data.Wish
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class AddEditDetailViewModel : ViewModel() {
    private val _wish = MutableStateFlow<Wish>(Wish())
    val wish = _wish.asStateFlow()

    fun editWish(wish: Wish){
        _wish.value = wish
    }

    fun updateTitle(title:String){
        _wish.update { prev->
            prev.copy(
                title = title
            )
        }
    }

    fun updateDescription(description:String){
        _wish.update { prev->
            prev.copy(
                description = description
            )
        }
    }

    fun isNotEmpty():Boolean =
        wish.value.title.isNotEmpty() && wish.value.description.isNotEmpty()

}