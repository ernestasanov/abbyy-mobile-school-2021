package com.learning.navigation.ui.main

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.learning.navigation.R

/**
 * A simple [Fragment] subclass.
 * Use the [DialogFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogFragment : androidx.fragment.app.DialogFragment() {

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment DialogFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            DialogFragment().apply {
                arguments = Bundle()
            }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }
}