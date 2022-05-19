package com.hfad.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel : ViewModel() {
    private val _imageDisplay = MutableLiveData<String>("")
    val imageDisplay: LiveData<String>
        get() = _imageDisplay
    private val _nameDisplay = MutableLiveData<String>("")
    val nameDisplay: LiveData<String>
        get() = _nameDisplay
    private val _heightDisplay = MutableLiveData<String>("")
    val heightDisplay: LiveData<String>
        get() = _heightDisplay
    private val _weightDisplay = MutableLiveData<String>("")
    val weightDisplay: LiveData<String>
        get() = _weightDisplay
}