package com.student.tutorial.helper.util

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import java.net.URI
import java.net.URL
import java.util.regex.Pattern


object UtilTool {
    private const val DEBUG_LOG = "DEBUG_LOG"

    fun getId(urlFilms: String): String = Pattern.compile("/").split(URI(urlFilms).path).last()

    fun genUrl(url: String) = URL(url).protocol + "://" + URL(url).host

    fun genUrlPath(url: String) = if (url.isNotBlank()) URL(url).path.substring(1) else ""

    val getUserAgent = StringBuilder()
        .append(System.getProperty("java.vm.name"))
        .append("/")
        .append(System.getProperty("java.vm.version"))
        .append("(")
        .append(System.getProperty("os.name"))
        .append("; Android ")
        .append(Build.VERSION.RELEASE)
        .append(";")
        .append(Build.MODEL)
        .append(";")
        .append(Build.ID)
        .append(")").toString()


    fun Any.debugLog(mes: Any?, name: String? = DEBUG_LOG) {
        // if (BuildConfig.DEBUG) {
        var sb = mes.toString()
        val i = 3000
        while (sb.length > i) {
            Log.d(
                name,
                "Class == " + this.javaClass.simpleName + ": Messages == " + sb.substring(0, i)
            )
            sb = sb.substring(i)
        }
        Log.d(name, "Class == " + this.javaClass.simpleName + ": Messages == " + sb)
        // }
    }

    fun Context.showToast(message: Any?) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
    }
    fun Context.showToastShort(message: Any?) {
        Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()
    }

     fun Context.printInputLanguages() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        val ims = imm!!.enabledInputMethodList
        for (method in ims) {
            val subMethods = imm.getEnabledInputMethodSubtypeList(method, true)
            for (subMethod in subMethods) {
                if (subMethod.mode == "keyboard") {
                    val currentLocale = subMethod.languageTag

                    debugLog(currentLocale , "DEBUG_LOG_8000")
                }
            }
        }
    }

    fun String.isOnlyAlphabets(): Boolean {
        return Pattern.compile("^[а-яА-Я0-9.-]+$").matcher(this).matches()
    }
    fun String.isBasic(): Boolean {
        return Pattern.compile("^[А-Я]{4}\\.[0-9]{6}\\.[0-9]{3}$").matcher(this).matches()
    }
    fun String.isMinor(): Boolean {
        return Pattern.compile("^[А-Я]{4}\\.[0-9]{6}\\.[0-9]{3}$").matcher(this).matches()
    }
    fun String.isPExecutions(): Boolean {
        return Pattern.compile("^[А-Я]{4}\\.[0-9]{6}\\.[0-9]{3}-[0-9]{2}$").matcher(this).matches()
    }
    fun String.isDExecutions(): Boolean {
        return Pattern.compile("^[А-Я]{4}\\.[0-9]{6}\\.[0-9]{3}-[0-9]{2}\\.[0-9]{2}$").matcher(this).matches()
    }


}