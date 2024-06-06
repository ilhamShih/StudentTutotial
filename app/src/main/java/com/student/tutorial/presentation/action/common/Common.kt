package com.student.tutorial.presentation.action.common

import android.content.Context
import com.student.tutorial.config.Constant.GET_CLASS
import com.student.tutorial.config.Constant.GET_CLASS_CONTENT
import com.student.tutorial.presentation.action.adapter.popup.DataPopup
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object Common {
    fun Context.getClassesContents(): MutableList<String> {
        val inputStream = assets.open(GET_CLASS_CONTENT).bufferedReader()
        val lines = mutableListOf<String>()
        var line: String?
        while (inputStream.readLine().also { line = it } != null) {
            lines.add(line ?: "")
        }
        inputStream.close()
        return lines
    }

    suspend fun Context.getParentClass() = withContext(Dispatchers.IO) {
        val list = mutableListOf<DataPopup>()
        assets.open(GET_CLASS).bufferedReader().useLines { line ->
            line.forEach {
                val keyClass = it.substringAfter("-")
                list.add(
                    DataPopup(
                        typeClass = TypeClass.CLASS,
                        allNameNormal = keyClass,
                        name = keyClass,
                        key = keyClass
                    )
                )
            }
        }
        list
    }

    suspend fun MutableList<String>.getSubClass(key: String, subClass: TypeClass) =
        withContext(Dispatchers.IO) {
            val list = mutableListOf<DataPopup>()
            val lists = mutableListOf<String>()
            var counter = 0
            (0..9).forEach { _ ->
                filter { item ->
                    val prefix = item.substringAfter("- ")
                    val keyCounter = "$key$counter"
                    val size = keyCounter.length
                    val startsWith = prefix.startsWith(keyCounter)
                    startsWith && prefix.length > size && prefix[size].isLetter()
                }.forEach {
                    lists.add(it)
                }
                counter++
            }
            lists.forEach {
                val normalString = it.substringAfter("- ")
                val name = normalString.filter { txt -> txt.isLetter() }
                val number = normalString.filter { txt -> txt.isDigit() }
                list.add(
                    DataPopup(
                        typeClass = subClass,
                        allNameNormal = normalString,
                        name = name,
                        key = number
                    )
                )
            }

            list
        }
}