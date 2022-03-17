package com.example.fragmentsample

import androidx.fragment.app.Fragment

class StateFragment : Fragment() {
    init {
        retainInstance = true
    }
    var counter = 0
    companion object {
        const val TAG = "StateFragment"
    }
}