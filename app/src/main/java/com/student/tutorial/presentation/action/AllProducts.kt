package com.student.tutorial.presentation.action

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.student.tutorial.R
import com.student.tutorial.config.Constant.INTENT_COMMENT
import com.student.tutorial.config.Constant.INTENT_DOC
import com.student.tutorial.config.Constant.INTENT_KEY
import com.student.tutorial.core.model.AllProductsData
import com.student.tutorial.core.model.AllProductsTutorData
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.databinding.ActivityProductBinding
import com.student.tutorial.helper.dialog.CommonDialog.dialogDelete
import com.student.tutorial.helper.dialog.CommonDialog.dialogInfoProduct
import com.student.tutorial.helper.dialog.DialogCommand
import com.student.tutorial.helper.save.Save.isUserHash
import com.student.tutorial.helper.util.UtilTool.showToast
import com.student.tutorial.presentation.action.adapter.product.AdapterProduct
import com.student.tutorial.presentation.action.adapter.product.OnClickAdapterProduct
import com.student.tutorial.presentation.action.common.TypeClick
import com.student.tutorial.presentation.mvvm.CommonViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllProducts : AppCompatActivity(), OnClickAdapterProduct {
    private var _binding: ActivityProductBinding? = null
    private var _adapterProduct: AdapterProduct? = null
    private val commonViewModel: CommonViewModel by viewModels()
    private val binding
        get() = _binding ?: throw RuntimeException("InsertProductBinding == null")
    var search: Editable? = null

    private val adapterProduct
        get() = _adapterProduct ?: throw RuntimeException("AdapterProduct == null")
    private val dialogViewInfo: View by lazy {
        layoutInflater.inflate(
            R.layout.dialog_info,
            null
        )
    }

    private val dialogViewDelete: View by lazy {
        layoutInflater.inflate(
            R.layout.dialog_delete,
            null
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _adapterProduct = AdapterProduct(this , this)
        binding.recyclerView.adapter = adapterProduct

        binding.imageAddButton.setOnClickListener {
            startActivity(Intent(this, InsertProduct::class.java))
        }
        search()
    }

    private fun search() {
        binding.searchField.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                adapterProduct.filter.filter(s)
                search = s
            }

        })
    }

    override fun onResume() {
        super.onResume()
        getAllProducts()
    }


    private suspend fun Int.isShowProgressBar() {
        withContext(Dispatchers.Main) {
            binding.progress.visibility = this@isShowProgressBar
        }
    }

    override fun onClickProduct(them: AllProductsData.Product, typeClick: TypeClick) {
        super.onClickProduct(them, typeClick)
        when (typeClick) {
            TypeClick.INFO -> {
                dialogInfoProduct(dialogViewInfo, them) {
                    //  showToast("INFO")
                }
            }

            TypeClick.DELETE -> {
                dialogDelete(dialogViewDelete) {
                    if (it == DialogCommand.OK) {
                        them.nameKey?.let { res ->
                            delete(res)
                        }
                    }
                }
            }

            TypeClick.EDIT -> {
                val intents = Intent(this, InsertProduct::class.java)
                them.nameKey?.let {
                    intents.putExtra(INTENT_KEY, them.nameKey)
                    intents.putExtra(INTENT_COMMENT, them.comment)
                    intents.putExtra(INTENT_DOC, them.docName)
                    startActivity(intents)
                }
            }
        }

    }

    private fun delete(nameKey: String) {
        lifecycleScope.launch {
            View.VISIBLE.isShowProgressBar()
            val result = commonViewModel.getDeleteProducts(isUserHash, nameKey)
            when (result?.resultHttp) {
                ResultHttp.LOADING -> {}
                ResultHttp.OK -> {
                    View.GONE.isShowProgressBar()
                    showToast(result.message)
                     getAllProducts()

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
    }

    /*private fun getAllProductsTutor() {
        lifecycleScope.launch {
            val result = commonViewModel.getAllProductsTutorials()
            when (result?.resultHttp) {
                ResultHttp.LOADING -> {}
                ResultHttp.OK -> { adapterProduct.submitList(result.product?.toMutableList()) }
                ResultHttp.ERROR -> {}
                ResultHttp.EXCEPTION -> {}
                else -> {}
            }
        }
    }*/


     private fun getAllProducts() {
        lifecycleScope.launch {
            View.VISIBLE.isShowProgressBar()
            val result = commonViewModel.getAllProducts()
            when (result?.resultHttp) {
                ResultHttp.LOADING -> {}
                ResultHttp.OK -> {
                    View.GONE.isShowProgressBar()
                    adapterProduct.setFilterList(result.product?.reversed())
                    adapterProduct.submitList(result.product?.reversed()) {
                        binding.recyclerView.scrollToPosition(0)
                        adapterProduct.filter.filter(search)
                    }
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
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}