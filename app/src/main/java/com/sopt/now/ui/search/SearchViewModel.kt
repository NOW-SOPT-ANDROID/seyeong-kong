package com.sopt.now.ui.search

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.sopt.now.data.search.Repo
import java.io.IOException
import java.nio.charset.StandardCharsets

class SearchViewModel : ViewModel() {
    private val _repoList = MutableLiveData<List<Repo>>()
    val repoList: LiveData<List<Repo>> = _repoList

    fun loadJson(context: Context) {
        val fileName = "fake_repo_list.json"
        try {
            context.assets.open(fileName).use { inputStream ->
                val json = inputStream.readBytes().toString(StandardCharsets.UTF_8)
                val repoList = parseJson(json)
                _repoList.value = repoList
            }
        } catch (e: IOException) {
            Log.e("SearchViewModel", "Error loadJson", e)
        }
    }

    private fun parseJson(jsonString: String): List<Repo> {
        return try {
            Gson().fromJson(jsonString, Array<Repo>::class.java).toList()
        } catch (e: Exception) {
            Log.e("SearchViewModel", "Error parseJson", e)
            emptyList()
        }
    }
}
