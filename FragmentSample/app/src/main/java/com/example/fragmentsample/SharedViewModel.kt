package com.example.fragmentsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val counterData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    fun updateData(counter: Int) {
        counterData.value = counter
    }
    val counter: LiveData<Int>
        get() = counterData
}