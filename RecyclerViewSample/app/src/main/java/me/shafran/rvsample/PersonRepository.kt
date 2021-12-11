package me.shafran.rvsample

import android.content.Context
import android.text.TextUtils
import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.util.*
import kotlin.coroutines.suspendCoroutine

object PersonRepository {
	private val PERSON_LIST = mutableListOf<Person>()
	private fun loadSomethingBlocking(): Int {
		return runBlocking { loadSomething() }
	}
	private fun loadSomething(onLoaded: (Int) -> Unit, onFailed: (Exception) -> Unit) {
		Thread {
			try {
				onLoaded(loadSomethingBlocking())
			} catch (e: Exception) {
				onFailed(e)
			}
		}.start()
	}
	suspend fun loadSomething(): Int {
		return suspendCoroutine { continuation ->
			loadSomething({
				continuation.resumeWith(Result.success(it))
			}, {
				continuation.resumeWith(Result.failure(it))
			})
		}
	}
	suspend fun initialize(context: Context, onProgress: suspend (Float) -> Unit): List<Person> = withContext(Dispatchers.IO) {
		try {
			BufferedReader(InputStreamReader(context.assets.open("names.txt"))).use { reader ->
				var name = reader.readLine()
				var id: Long = 0
				while (!TextUtils.isEmpty(name)) {
					PERSON_LIST.add(Person(id, name))
					++id
					if (id.toInt() % 30 == 0) {
						launch {
							withContext(Dispatchers.Main) {
								onProgress(id / 346.0f)
							}
						}
						delay(1000)
					}
					name = reader.readLine()
				}
			}
		} catch(e: IOException) {
			e.printStackTrace()
			// Ничего не делать
		}
		return@withContext PERSON_LIST.toList()
	}

	fun getPersonList(): List<Person> {
		return PERSON_LIST.toList()
	}

	fun getPersonById(id: Long): Person {
		return PERSON_LIST[id.toInt()]
	}
}