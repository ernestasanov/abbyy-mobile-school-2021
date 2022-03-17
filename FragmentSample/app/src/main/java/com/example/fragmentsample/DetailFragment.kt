package com.example.fragmentsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels

class DetailFragment : Fragment() {
	private lateinit var stateFragment: StateFragment
	private lateinit var textView: TextView
	private val viewModel: SharedViewModel by activityViewModels()
	private var counter = 0
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		stateFragment =
			(parentFragmentManager.findFragmentByTag(StateFragment.TAG) ?: run {
				val stateFragment = StateFragment()
				parentFragmentManager.beginTransaction()
					.add(stateFragment, StateFragment.TAG)
					.commit()
				stateFragment
			}) as StateFragment
	}
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?): View? {
		return inflater.inflate(R.layout.fragment_detail, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		textView = view.findViewById(R.id.detailTextView)
		textView.text = "${stateFragment.counter}"
		val changeTextButton = view.findViewById<Button>(R.id.changeTextButton)
		changeTextButton.setOnClickListener {
			viewModel.updateData(++counter)
		}
	}

	companion object {
		const val TAG = "DetailFragment"
		private const val NAME_KEY = "NAME_KEY"
		fun newInstance(name: String): Fragment {
			val fragment: Fragment = DetailFragment()
			val arguments = Bundle()
			arguments.putString(NAME_KEY, name)
			fragment.arguments = arguments
			return fragment
		}
	}
}