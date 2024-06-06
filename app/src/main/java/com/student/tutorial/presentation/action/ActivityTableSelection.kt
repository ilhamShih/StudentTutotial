package com.student.tutorial.presentation.action

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.student.tutorial.databinding.ActivityTableSelectionBinding
import com.student.tutorial.helper.save.Save.isUserName
import com.student.tutorial.helper.save.Save.isUserType

class ActivityTableSelection : AppCompatActivity() {
    private var _binding: ActivityTableSelectionBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("ActivityTableSelectionBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityTableSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (isUserType == 1) {
            binding.tblUser.visibility = View.VISIBLE
        } else {
            binding.tblUser.visibility = View.GONE
        }

        binding.imgAccount.setOnClickListener {
            startActivity(Intent(this, AutActivity::class.java))
            finishAffinity()
        }
        binding.tblUser.setOnClickListener {
            startActivity(Intent(this, ActivityUsers::class.java))
        }

        binding.tblProduct.setOnClickListener {
            startActivity(Intent(this, AllProducts::class.java))
        }

        binding.txtUserType.text = if (isUserType == 1) "Администратор" else "Пользователь"
        binding.txtUserName.text = isUserName

    }
}