package com.hfad.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DetailViewModelFactory(private val finalResult1: String, private val finalResult2: String,
                             private val finalResult3: String,
                             private val finalResult4: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java))
            return DetailViewModel() as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}