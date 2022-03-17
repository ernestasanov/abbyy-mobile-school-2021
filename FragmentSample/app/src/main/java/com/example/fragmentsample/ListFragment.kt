package com.example.fragmentsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer

class ListFragment : Fragment() {
	private val viewModel: SharedViewModel by activityViewModels()
	private lateinit var someTextView: TextView
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_list, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		view.findViewById<View>(R.id.openDetailButton).setOnClickListener {
			(requireActivity() as DemoActivity).showDetailFragment("ghjkajsjakasd")
		}
		someTextView = view.findViewById(R.id.someText)
		viewModel.counter.observe(viewLifecycleOwner) { counter ->
			someTextView.text = "$counter"
		}
	}

	companion object {
		const val TAG = "ListFragment"
		fun newInstance(): Fragment {
			return ListFragment()
		}
	}
}