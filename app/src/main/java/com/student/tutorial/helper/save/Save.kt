package com.student.tutorial.helper.save

import android.content.Context

object Save {

    var Context.isUserInit: Boolean
        get() = get(USER_INIT, false)
        set(value) {
            set(USER_INIT, value)
        }

    var Context.isUserHash: String
        get() = get(USER_HASH, "")
        set(value) {
            set(USER_HASH, value)
        }

    var Context.isUserType: Int
        get() = get(USER_TYPE, 0)
        set(value) {
            set(USER_TYPE, value)
        }

    var Context.isUserName: String
        get() = get(USER_NAME, "")
        set(value) {
            set(USER_NAME, value)
        }


    fun Context.set(name: String, value: Any?) {
        val prefs = getSharedPreferences(name, Context.MODE_PRIVATE)
        when (value) {
            is String -> prefs.edit().putString(name, value).apply()
            is Int -> prefs.edit().putInt(name, value).apply()
            is Boolean -> prefs.edit().putBoolean(name, value).apply()
            else -> prefs.edit().putString(name, value.toString()).apply()
        }
    }

    fun <T> Context.get(name: String, def: T): T {
        try {
            val prefs = getSharedPreferences(name, Context.MODE_PRIVATE)
            if (prefs.all.containsKey(name))
                return prefs.all[name] as T
            return def
        } catch (e: Exception) {
            return def
        }
    }

    private const val USER_HASH = "USER_HASH"
    private const val USER_TYPE = "USER_TYPE"
    private const val USER_NAME = "USER_NAME"
    private const val USER_INIT = "USER_INIT"
}