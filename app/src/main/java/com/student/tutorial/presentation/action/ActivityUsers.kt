package com.student.tutorial.presentation.action

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.student.tutorial.R
import com.student.tutorial.config.Constant.EDIT
import com.student.tutorial.config.Constant.INSERT
import com.student.tutorial.config.Constant.INTENT_COMMAND
import com.student.tutorial.config.Constant.INTENT_ID
import com.student.tutorial.config.Constant.INTENT_LOGIN
import com.student.tutorial.config.Constant.INTENT_NAME
import com.student.tutorial.config.Constant.INTENT_TYPE
import com.student.tutorial.core.model.AllProductsTutorData
import com.student.tutorial.core.model.AllUsersData
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.databinding.ActivityUsersBinding
import com.student.tutorial.helper.dialog.CommonDialog.dialogDelete
import com.student.tutorial.helper.dialog.DialogCommand
import com.student.tutorial.helper.save.Save.isUserHash
import com.student.tutorial.helper.util.UtilTool.debugLog
import com.student.tutorial.helper.util.UtilTool.showToast
import com.student.tutorial.presentation.action.adapter.users.AdapterUsers
import com.student.tutorial.presentation.action.adapter.users.OnClickAdapterUsers
import com.student.tutorial.presentation.action.common.TypeClick
import com.student.tutorial.presentation.mvvm.CommonViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityUsers : AppCompatActivity(), OnClickAdapterUsers {
    private var _binding: ActivityUsersBinding? = null
    private var _adapterProduct: AdapterUsers? = null
    private val commonViewModel: CommonViewModel by viewModels()
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityUsersBinding == null")

    private val adapterUsers
        get() = _adapterProduct ?: throw RuntimeException("AdapterUsers == null")

    private val dialogViewDelete: View by lazy {
        layoutInflater.inflate(
            R.layout.dialog_delete,
            null
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        _adapterProduct = AdapterUsers(this)
        binding.recyclerView.adapter = adapterUsers

        binding.imageAddButton.setOnClickListener {
            val intents = Intent(this, ActivityUserEdit::class.java)
            intents.putExtra(INTENT_COMMAND, INSERT)
            startActivity(intents)
        }

    }

    override fun onResume() {
        super.onResume()
        getAllProducts(isUserHash)
    }

    private fun getAllProducts(hash: String) {
        lifecycleScope.launch {
            View.VISIBLE.isShowProgressBar()
            val result = commonViewModel.getAllUsers(hash)
            when (result?.resultHttp) {
                ResultHttp.LOADING -> {}
                ResultHttp.OK -> {
                    debugLog(result.users)
                    View.GONE.isShowProgressBar()
                    adapterUsers.submitList(result.users?.reversed()) {
                        binding.recyclerView.scrollToPosition(0)
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

    private suspend fun Int.isShowProgressBar() {
        withContext(Dispatchers.Main) {
            binding.progress.visibility = this@isShowProgressBar
        }
    }

    override fun onClickUsers(them: AllUsersData.Users, typeClick: TypeClick) {
        super.onClickUsers(them, typeClick)
        when (typeClick) {
            TypeClick.DELETE -> {
                dialogDelete(dialogViewDelete) {
                    if (it == DialogCommand.OK) {
                        them.id?.let { res ->
                            delete(res)
                        }
                    }
                }
            }

            TypeClick.EDIT -> {
                val intents = Intent(this, ActivityUserEdit::class.java)
                them.id?.let {
                    intents.putExtra(INTENT_ID, them.id)
                    intents.putExtra(INTENT_LOGIN, them.login)
                    intents.putExtra(INTENT_NAME, them.name)
                    intents.putExtra(INTENT_TYPE, them.userType)
                    intents.putExtra(INTENT_COMMAND, EDIT)
                    startActivity(intents)
                }
            }

            else -> {}
        }
    }

    private fun delete(idUser: Int) {
        lifecycleScope.launch {
            View.VISIBLE.isShowProgressBar()
            val result = commonViewModel.getDeleteUser(isUserHash, idUser)
            when (result?.resultHttp) {
                ResultHttp.LOADING -> {}
                ResultHttp.OK -> {
                    View.GONE.isShowProgressBar()
                    showToast(result.message)
                    getAllProducts(isUserHash)

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
}