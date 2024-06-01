package com.eskaya.task_sanstech.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AppPreferences private constructor(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "MyAppPrefs"
        private var instance: AppPreferences? = null

        fun getInstance(context: Context): AppPreferences {
            if (instance == null) {
                instance = AppPreferences(context)
            }
            return instance!!
        }
    }

    private fun getFavorites(): MutableSet<Int> {
        val json = sharedPreferences.getString("FAVORITE_LIST", null)
        if (json != null) {
            val type = object : TypeToken<MutableSet<Int>>() {}.type
            return Gson().fromJson(json, type)
        }
        return mutableSetOf()
    }


    fun addFavorite(matchId: Int) {
        val favorites = getFavorites()
        favorites.add(matchId)
        val json = Gson().toJson(favorites)
        sharedPreferences.edit().putString("FAVORITE_LIST", json).apply()
    }

    fun removeFavorite(matchId: Int) {
        val favorites = getFavorites()
        favorites.remove(matchId)
        val json = Gson().toJson(favorites)
        sharedPreferences.edit().putString("FAVORITE_LIST", json).apply()
    }

    fun isFavorite(matchId: Int): Boolean {
        val favorites = getFavorites()
        return favorites.contains(matchId)
    }
}







