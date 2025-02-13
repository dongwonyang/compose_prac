package com.example.compose_prac.ui.screen.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_prac.local.Wish
import com.example.compose_prac.local.WishRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddEditDetailViewModel @Inject constructor(
    private val wishRepository: WishRepository
) : ViewModel() {
    private val _wish = MutableStateFlow<Wish>(Wish())
    val wish = _wish.asStateFlow()

    fun editWish(id: Long) = viewModelScope.launch{
        wishRepository.getWishById(id)?.let {
            _wish.value = it
        }
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

    fun updateWish(isSuccess: () -> Unit) = viewModelScope.launch{
        val wish = wish.value
        if(wish.id != 0L) wishRepository.update(wish)
        else wishRepository.insert(wish)

        isSuccess()
    }
}