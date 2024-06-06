package com.student.tutorial.presentation.action

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.student.tutorial.config.Constant.EDIT
import com.student.tutorial.config.Constant.INTENT_COMMAND
import com.student.tutorial.config.Constant.INTENT_ID
import com.student.tutorial.config.Constant.INTENT_LOGIN
import com.student.tutorial.config.Constant.INTENT_NAME
import com.student.tutorial.config.Constant.INTENT_TYPE
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.databinding.ActivityUserEditBinding
import com.student.tutorial.helper.save.Save.isUserHash
import com.student.tutorial.helper.util.UtilTool.showToast
import com.student.tutorial.presentation.mvvm.CommonViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ActivityUserEdit : AppCompatActivity() {

    private var _binding: ActivityUserEditBinding? = null
    private val commonViewModel: CommonViewModel by viewModels()
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityUsersBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityUserEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (getCommand() == EDIT) {
            binding.editTxtType.setText(getType().toString())
            binding.editTxtLogin.setText(getLogin())
            binding.editTxtName.setText(getName())
        }else{
            binding.btnOk.text = "Отправить"
        }
        binding.btnOk.setOnClickListener {
            if (getCommand() == EDIT) {
                update(
                    isUserHash,
                    getId(),
                    binding.editTxtType.text.toString().toInt(),
                    binding.editTxtName.text.toString().trim(),
                    binding.editTxtLogin.text.toString().trim(),
                    binding.editTxtPass.text.toString().trim()
                )
            } else {
                newUser(
                    isUserHash,
                    binding.editTxtLogin.text.toString().trim(),
                    binding.editTxtPass.text.toString().trim(),
                    binding.editTxtType.text.toString().toInt(),
                    binding.editTxtName.text.toString().trim()
                )
            }
        }
    }

    private fun update(
        hash: String,
        idUser: Int,
        typeUser: Int,
        nameUser: String,
        loginUser: String,
        passUser: String,
    ) {
        lifecycleScope.launch {
            binding.btnOk.isEnabled = false
            View.VISIBLE.isShowProgressBar()
            val result =
                commonViewModel.getUserUpdate(hash, idUser, typeUser, nameUser, loginUser, passUser)



            when (result?.resultHttp) {
                ResultHttp.LOADING -> {}
                ResultHttp.OK -> {
                    binding.btnOk.isEnabled = true
                    View.GONE.isShowProgressBar()
                    showToast(result.message)


                }

                ResultHttp.ERROR -> {
                    View.GONE.isShowProgressBar()
                    showToast("ERROR")
                    binding.btnOk.isEnabled = true
                }

                ResultHttp.EXCEPTION -> {
                    View.GONE.isShowProgressBar()
                    showToast("EXCEPTION")
                    binding.btnOk.isEnabled = true
                }

                else -> {
                    binding.btnOk.isEnabled = true
                    showToast("Неизвестная ошибка")
                }
            }
        }
    }

    private fun newUser(
        hash: String,
        loginUser: String,
        passUser: String,
        typeUser: Int,
        nameUser: String
    ) {
        lifecycleScope.launch {
            binding.btnOk.isEnabled = false
            View.VISIBLE.isShowProgressBar()
            val result =
                commonViewModel.getAddNewUser(hash, loginUser, passUser, typeUser, nameUser)
            when (result?.resultHttp) {
                ResultHttp.LOADING -> {}
                ResultHttp.OK -> {
                    binding.btnOk.isEnabled = true
                    View.GONE.isShowProgressBar()
                    showToast(result.message)
                }

                ResultHttp.ERROR -> {
                    View.GONE.isShowProgressBar()
                    showToast("ERROR")
                    binding.btnOk.isEnabled = true
                }

                ResultHttp.EXCEPTION -> {
                    View.GONE.isShowProgressBar()
                    showToast("EXCEPTION")
                    binding.btnOk.isEnabled = true
                }

                else -> {
                    binding.btnOk.isEnabled = true
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

    private fun getCommand() = intent.getIntExtra(INTENT_COMMAND, 0)
    private fun getId() = intent.getIntExtra(INTENT_ID, 0)
    private fun getLogin() = intent.getStringExtra(INTENT_LOGIN) ?: ""
    private fun getName() = intent.getStringExtra(INTENT_NAME) ?: ""
    private fun getType() = intent.getIntExtra(INTENT_TYPE, 0)
}