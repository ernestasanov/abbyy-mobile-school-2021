package me.shafran.rvsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class PersonListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_person_list)

		val recyclerView: RecyclerView = findViewById(R.id.personRecyclerView)
		recyclerView.layoutManager = LinearLayoutManager(this)
		recyclerView.setHasFixedSize(true)
		recyclerView.recycledViewPool.setMaxRecycledViews(0, 5)

		val adapter = PersonAdapter()
		recyclerView.adapter = adapter

		val thread = Thread {
			PersonRepository.initialize(this)
			adapter.personList = PersonRepository.getPersonList()
			recyclerView.post {
				adapter.notifyDataSetChanged()
			}
		}
		thread.start()
		adapter.listener = { personId ->
			val intent = PersonDetailActivity.getIntent(this, personId)
			startActivity(intent)
		}
	}
}