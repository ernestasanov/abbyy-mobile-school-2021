package me.shafran.rvsample

import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LeftPersonViewHolder(itemView: LinearLayout, listener: (Long) -> Unit)
	: RecyclerView.ViewHolder(itemView) {
	protected val textView: TextView = itemView.findViewById(R.id.personNameTextView)
	private lateinit var data: Person

	init {
		textView.setOnClickListener {
			listener(data.id)
		}
	}

	fun bind(person: Person) {
		data = person
		textView.text = data.name
	}

}