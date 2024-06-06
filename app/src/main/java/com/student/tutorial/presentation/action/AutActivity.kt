package com.student.tutorial.presentation.action

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.student.tutorial.core.model.ResultHttp
import com.student.tutorial.databinding.AutMainBinding
import com.student.tutorial.helper.save.Save.isUserHash
import com.student.tutorial.helper.save.Save.isUserInit
import com.student.tutorial.helper.save.Save.isUserName
import com.student.tutorial.helper.save.Save.isUserType
import com.student.tutorial.helper.util.UtilTool.showToast
import com.student.tutorial.presentation.mvvm.CommonViewModel
import kotlinx.coroutines.launch

class AutActivity : AppCompatActivity() {

    private val commonViewModel: CommonViewModel by viewModels()

    private var _binding: AutMainBinding? = null
    private val binding get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = AutMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnOk.setOnClickListener {
            binding.progress.visibility = View.VISIBLE
            autUser(binding.login.text.toString(), binding.pass.text.toString())
        }
        binding.btnUnAut.setOnClickListener {
            isUserInit = false
            isUserInit()
        }
        binding.btnNext.setOnClickListener {
            startActivity(Intent(this, ActivityTableSelection::class.java))
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        synchro(isUserHash)


    }

    private fun isUserInit() {
        if (!isUserInit) {
            binding.relativeEditTxt.visibility = View.VISIBLE
            binding.relativeUserTxt.visibility = View.INVISIBLE
            commonViewModel.autLiveData.observe(this) {
                if (it) {
                    binding.progress.visibility = View.INVISIBLE
                    binding.relativeEditTxt.visibility = View.INVISIBLE
                    binding.relativeUserTxt.visibility = View.VISIBLE
                    isUserInit = true
                    binding.txtUserName.text = " Имя: $isUserName"
                    binding.txtUserType.text =
                        if (isUserType == 1) "Вы Администратор" else "Вы Пользователь"


                } else {
                    binding.progress.visibility = View.INVISIBLE
                    showToast("Ошибка Авторизации")
                }
            }
        } else {
            binding.relativeEditTxt.visibility = View.INVISIBLE
            binding.relativeUserTxt.visibility = View.VISIBLE
            binding.txtUserName.text = " Имя: $isUserName"
            binding.txtUserType.text =
                if (isUserType == 1) "Вы Администратор" else "Вы Пользователь"
        }
    }

    private fun autUser(login: String, pass: String) {
        lifecycleScope.launch {
            val user = commonViewModel.getAutUser(login, pass)
            if (user != null) {

                when (user.resultHttp) {
                    ResultHttp.LOADING -> {
                        binding.progress.visibility = View.VISIBLE
                    }

                    ResultHttp.OK -> {
                        if (user.result == true) {
                            user.user?.hashCode?.let {
                                user.user.userType?.let { t ->
                                    isUserType = t
                                }
                                user.user.name?.let { n ->
                                    isUserName = n
                                }
                                isUserHash = it
                                commonViewModel.setAutLiveData(true)
                            }
                        } else {
                            commonViewModel.setAutLiveData(false)
                        }
                    }

                    ResultHttp.ERROR -> {
                        commonViewModel.setAutLiveData(false)
                    }

                    ResultHttp.EXCEPTION -> {
                        commonViewModel.setAutLiveData(false)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun synchro(hash: String) {
        lifecycleScope.launch {
            binding.progress.visibility = View.VISIBLE
            val result = commonViewModel.getSynchroUser(hash)
            when (result?.resultHttp) {
                ResultHttp.LOADING -> {}
                ResultHttp.OK -> {
                    binding.progress.visibility = View.INVISIBLE
                    result.available?.let {
                        if (!it) {
                            isUserInit = false
                            isUserType = result.userType ?: 0
                            showToast(result.message)
                            isUserInit()
                        } else {
                            isUserType = result.userType ?: 0
                            isUserInit = true
                            isUserInit()
                        }
                    }


                }

                ResultHttp.ERROR -> {
                    binding.progress.visibility = View.INVISIBLE
                    showToast("ERROR")
                }

                ResultHttp.EXCEPTION -> {
                    binding.progress.visibility = View.INVISIBLE
                    showToast("EXCEPTION")
                }

                else -> {
                    showToast("Неизвестная ошибка")
                }
            }
        }
    }


}