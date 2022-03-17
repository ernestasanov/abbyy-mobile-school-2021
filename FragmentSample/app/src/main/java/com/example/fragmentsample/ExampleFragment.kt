package com.example.fragmentsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class ExampleFragment : Fragment() {
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_example, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val name = arguments?.getString("name") ?: requireContext().getString(R.string.app_name)
		view.findViewById<TextView>(R.id.nameView).text = name
	}

	companion object {
		fun newInstance(name: String): Fragment {
			val fragment = ExampleFragment()
			val bundle = Bundle()
			bundle.putString("name", name)
			fragment.arguments = bundle
			return fragment
		}
	}
}