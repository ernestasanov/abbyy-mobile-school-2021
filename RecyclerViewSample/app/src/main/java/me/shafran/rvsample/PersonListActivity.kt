package me.shafran.rvsample

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.*
import java.lang.ref.WeakReference
import kotlin.math.roundToInt

class PersonListActivity : AppCompatActivity() {

	//private lateinit var task: LoadTask

    private lateinit var coroutine: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)

        val recyclerView: RecyclerView = findViewById(R.id.personRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.recycledViewPool.setMaxRecycledViews(0, 5)

        val adapter = PersonAdapter()
        recyclerView.adapter = adapter

        val progressView = findViewById<TextView>(R.id.progressView)

		/*task = LoadTask(WeakReference(this), { progress ->
			progressView.text = "${(progress * 100).roundToInt()}%"
		}, { list ->
			adapter.personList = list
			adapter.notifyDataSetChanged()
		})
		task.execute()*/
        lifecycleScope.launch(context = Dispatchers.Main) {
            progressView.text = "0%"
            adapter.personList =
                PersonRepository.initialize(this@PersonListActivity) { progress ->
                    progressView.text = "${(progress * 100).roundToInt()}%"
                }
            adapter.notifyDataSetChanged()
        }
        adapter.listener = { personId ->
            val intent = PersonDetailActivity.getIntent(this, personId)
            startActivity(intent)
        }
    }

	override fun onDestroy() {
		//task.cancel(true)
		super.onDestroy()
	}

	/*class LoadTask(
        private val context: WeakReference<Context>,
        private val onProgress: (Float) -> Unit,
        private val onComplete: (List<Person>) -> Unit
    ) : AsyncTask<Void, Float, List<Person>>() {
        override fun doInBackground(vararg params: Void?): List<Person> {
            PersonRepository.initialize(context.get() ?: return emptyList()) { progress ->
                this.publishProgress(progress * 100)
            }
            return PersonRepository.getPersonList()
        }

		override fun onProgressUpdate(vararg values: Float?) {
			super.onProgressUpdate(*values)
			values[0]?.let { onProgress(it) }
		}

		override fun onPostExecute(result: List<Person>?) {
			super.onPostExecute(result)
			result?.let { onComplete(it) }
		}
    }*/
}