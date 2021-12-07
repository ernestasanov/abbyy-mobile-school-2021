package me.shafran.rvsample

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class PersonAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
	companion object {
		private const val LEFT_VIEW_HOLDER_TYPE = 0
		private const val RIGHT_VIEW_HOLDER_TYPE = 1
	}

	var personList: List<Person> = emptyList()

	var listener: (Long)->Unit = {}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
		return when (viewType) {
			LEFT_VIEW_HOLDER_TYPE -> {
				val itemView = inflater.inflate(R.layout.person_list_item, parent, false) as LinearLayout
				LeftPersonViewHolder(itemView, listener)
			}
			RIGHT_VIEW_HOLDER_TYPE -> {
				val itemView = inflater.inflate(R.layout.person_right_list_item, parent, false) as ConstraintLayout
				RightPersonViewHolder(itemView, listener)
			}
			else -> throw IllegalStateException()
		}
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		when (holder) {
			is LeftPersonViewHolder ->
				holder.bind(personList[position])
			is RightPersonViewHolder ->
				holder.bind(personList[position])
		}
	}

	override fun getItemCount(): Int {
		return personList.size
	}

	override fun getItemViewType(position: Int): Int {
		return position % 2
	}
}