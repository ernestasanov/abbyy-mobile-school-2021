package me.shafran.rvsample

import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class RightPersonViewHolder(
    itemView: ConstraintLayout,
    listener: (Long) -> Unit
) : RecyclerView.ViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.personNameTextView)
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