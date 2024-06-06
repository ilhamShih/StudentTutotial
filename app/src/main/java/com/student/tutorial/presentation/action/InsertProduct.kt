package com.student.tutorial.presentation.action

import android.os.Bundle
import android.view.View
import android.widget.ListPopupWindow
import android.widget.SearchView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.student.tutorial.R
import com.student.tutorial.config.Constant.INTENT_COMMENT
import com.student.tutorial.config.Constant.INTENT_DOC
import com.student.tutorial.config.Constant.INTENT_KEY
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.databinding.InsertProductBinding
import com.student.tutorial.helper.save.Save.isUserHash
import com.student.tutorial.helper.save.Save.isUserName
import com.student.tutorial.helper.util.UtilTool.isBasic
import com.student.tutorial.helper.util.UtilTool.isDExecutions
import com.student.tutorial.helper.util.UtilTool.isMinor
import com.student.tutorial.helper.util.UtilTool.isOnlyAlphabets
import com.student.tutorial.helper.util.UtilTool.isPExecutions
import com.student.tutorial.helper.util.UtilTool.printInputLanguages
import com.student.tutorial.helper.util.UtilTool.showToast
import com.student.tutorial.helper.util.UtilTool.showToastShort
import com.student.tutorial.presentation.mvvm.CommonViewModel
import com.student.tutorial.presentation.action.adapter.popup.AdapterComplete
import com.student.tutorial.presentation.action.adapter.popup.DataPopup
import com.student.tutorial.presentation.action.adapter.popup.OnClickAdapterPopup
import com.student.tutorial.presentation.action.common.TypeClass
import com.student.tutorial.presentation.action.common.Common.getClassesContents
import com.student.tutorial.presentation.action.common.Common.getParentClass
import com.student.tutorial.presentation.action.common.Common.getSubClass
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale


class InsertProduct : AppCompatActivity(), OnClickAdapterPopup {
    private var _binding: InsertProductBinding? = null

    private var _apterComplete: AdapterComplete? = null
    private var lockMain = false
    private var listSubClass = mutableListOf<String>()
    private val commonViewModel: CommonViewModel by viewModels()
    private val stringBuilder = StringBuilder()

    private val binding
        get() = _binding ?: throw RuntimeException("InsertProductBinding == null")

    private val apterComplete
        get() = _apterComplete ?: throw RuntimeException("AdapterComplete == null")

    val suggestionsPopup: ListPopupWindow by lazy {
        ListPopupWindow(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = InsertProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initPopup()

        lifecycleScope.launch {
            search()
            listSubClass = getClassesContents()
        }
        binding.btnExit.setOnClickListener {
            productAdd()
        }
        printInputLanguages()

        if (getKeyName().isNotBlank()) {
            binding.searchEditTxt.setQuery(getKeyName(), false)
            binding.editTxt.setText(getComment())
            stringBuilder.append(getDoc())
        }

    }

    private fun initPopup() {
        _apterComplete = AdapterComplete(this, R.layout.ithem_spinner, this)
        suggestionsPopup.setAdapter(apterComplete)
        suggestionsPopup.anchorView = binding.searchEditTxt
        suggestionsPopup.height = 600
        suggestionsPopup.isModal = true
    }


    private fun search() {
        binding.searchEditTxt.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            private var controllerTxt = true
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { text ->
                    if (text.isOnlyAlphabets()) {
                        when (text.length) {
                            4 -> if (controllerTxt) {
                                lockMain = false
                                controllerTxt = false
                                val str = "${newText}."
                                binding.searchEditTxt.setQuery(
                                    str.uppercase(Locale.getDefault()),
                                    false
                                )
                                lifecycleScope.launch {
                                    View.VISIBLE.isShowProgressBar()
                                    val listClass = getParentClass()
                                    apterComplete.clear()
                                    apterComplete.addAll(listClass)
                                    suggestionsPopup.show()
                                    View.GONE.isShowProgressBar()
                                    //adapterSpinner.submitList(listClass)
                                }
                            } else {

                            }

                            in 0..4 -> {
                                lockMain = false
                                controllerTxt = true
                                stringBuilder.clear()
                                // adapterSpinner.submitList(null)
                            }

                            5 -> if (!controllerTxt) {
                                lockMain = false
                                val newTex = newText.substring(0, newText.length - 1)
                                val str = "${newTex}."
                                binding.searchEditTxt.setQuery(str.uppercase(), false)
                            } else {

                            }


                            in 5..7 -> {
                                controllerTxt = true
                                lockMain = true
                            }

                            11 -> {
                                if (controllerTxt) {
                                    controllerTxt = false
                                    val str = "${newText}."
                                    binding.searchEditTxt.setQuery(str.uppercase(), false)
                                } else {

                                }
                            }

                            /*in 12..13 -> {
                                controllerTxt = false
                            }*/

                            14 -> controllerTxt = true
                            15 -> {
                                if (controllerTxt) {
                                    controllerTxt = false
                                    val str = "${newText}-"
                                    binding.searchEditTxt.setQuery(str.uppercase(), false)
                                } else {

                                }
                            }

                            16 -> controllerTxt = false
                            17 -> controllerTxt = true
                            18 -> {
                                if (controllerTxt) {
                                    controllerTxt = false
                                    val str = "${newText}."
                                    binding.searchEditTxt.setQuery(str.uppercase(), false)
                                } else {

                                }
                            }


                            else -> {}
                        }
                    } else {
                        showToastShort("Смените язык ввода на Русский")

                    }


                }


                return false
            }

        })
    }

    fun String.titleCase() = replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }

    private fun DataPopup.submitListAdapter(lockL: Int = 0, str: String, type: TypeClass) {
        if (type != TypeClass.SUB_CLASS) {
            if (str.length == lockL) {
                val norm = str.substringBefore(".")
                binding.searchEditTxt.setQuery("$norm.${key}", false)
                stringBuilder.append("/").append(allNameNormal)
                lifecycleScope.launch {
                    type.addPopupContent(key)
                }
            }
        } else {
            if (!lockMain) {
                binding.searchEditTxt.setQuery("$str${key}", false)
                lifecycleScope.launch {
                    stringBuilder.append(allNameNormal)
                    TypeClass.SUB_CLASS.addPopupContent(key)
                }
            }
        }
    }

    private suspend fun Int.isShowProgressBar() {
        withContext(Dispatchers.Main) {
            binding.progress.visibility = this@isShowProgressBar
        }
    }

    private suspend fun TypeClass.addPopupContent(key: String) {
        View.VISIBLE.isShowProgressBar()
        suggestionsPopup.dismiss()
        apterComplete.clear()
        val list = listSubClass.getSubClass(key, this@addPopupContent)
        apterComplete.addAll(list)
        suggestionsPopup.show()
        View.GONE.isShowProgressBar()
    }

    private fun productAdd() {
        val numKey = binding.searchEditTxt.query.toString()
        val str = if (numKey.lastChars() == ".") {
            numKey.substring(0, numKey.length - 1)
        } else if (numKey.lastChars() == "-") {
            numKey.substring(0, numKey.length - 1)
        } else {
            numKey
        }
        binding.searchEditTxt.setQuery(str.uppercase(), false)

        if (str.length in 11..21)
            if (str.isBasic() || str.isMinor() || str.isPExecutions() || str.isDExecutions())
                lifecycleScope.launch {
                    View.VISIBLE.isShowProgressBar()
                    val result = commonViewModel.addProductAndUpdate(
                        isUserHash,
                        str,
                        binding.editTxt.text.toString(),
                        stringBuilder.toString(),
                        isUserName
                    )
                    when (result?.resultHttp) {
                        ResultHttp.LOADING -> {}
                        ResultHttp.OK -> {
                            View.GONE.isShowProgressBar()
                            showToast(result.message)
                        }

                        ResultHttp.ERROR -> {
                            View.GONE.isShowProgressBar()
                            showToast("ERROR")
                        }

                        ResultHttp.EXCEPTION -> {
                            View.GONE.isShowProgressBar()
                            showToast("EXCEPTION")
                        }

                        else -> {
                            showToast("Неизвестная ошибка")
                        }
                    }
                }
            else
                showToastShort("Не верный формат, проверьте данные")
        else
            showToastShort("Не верный формат, проверьте данные")
    }

    override fun onClickPopup(them: DataPopup) {
        super.onClickPopup(them)

        val str = binding.searchEditTxt.query.toString()
        if (str.contains(".")) {
            when (them.typeClass) {
                TypeClass.CLASS -> them.submitListAdapter(str = str, type = TypeClass.SUB_CLASS)
                TypeClass.SUB_CLASS -> them.submitListAdapter(7, str, TypeClass.GROUP)
                TypeClass.GROUP -> them.submitListAdapter(8, str, TypeClass.SUB_GROUP)
                TypeClass.SUB_GROUP -> them.submitListAdapter(9, str, TypeClass.TYPE)
                TypeClass.TYPE -> them.submitListAdapter(10, str, TypeClass.TYPE)
            }
        }
    }

    private fun String.lastChars() =
        if (length > 1) {
            substring(length - 1)
        } else ""


    private fun getKeyName() = intent.getStringExtra(INTENT_KEY) ?: ""
    private fun getComment() = intent.getStringExtra(INTENT_COMMENT) ?: ""
    private fun getDoc() = intent.getStringExtra(INTENT_DOC) ?: ""


}