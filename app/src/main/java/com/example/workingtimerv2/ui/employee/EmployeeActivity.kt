
package com.example.workingtimerv2.ui.employee


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.workingtimerv2.R
import com.example.workingtimerv2.base.BaseActivity
import com.example.workingtimerv2.databinding.ActivityEmployeeBinding
import com.example.workingtimerv2.model.AppUser
import com.example.workingtimerv2.ui.login.LoginActivity

class EmployeeActivity : BaseActivity<ActivityEmployeeBinding, EmployeeViewModel>(), Navigator {

    lateinit var user: AppUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val binding = DataBindingUtil.setContentView<ActivityEmployeeBinding>(this, R.layout.activity_employee)
        //val viewModel = ViewModelProvider(this)[EmployeeViewModel::class.java]
        //binding.vm = viewModel

        viewDataBinding.vm = viewModel
        viewModel.navigator = this
        // I call the startTimer function from viewModel to start the timer when Activity is created
        // When activity is created means the user is successfully signed in
        user = intent.getParcelableExtra("name")!!
        viewModel.setDate()
        viewModel.setUserName(user.name!!)

        val startButton: AppCompatImageButton = findViewById(R.id.start_button)
        val pauseButton: AppCompatImageButton = findViewById(R.id.pause_button)

        viewModel.startButton = startButton
        viewModel.pauseButton = pauseButton
    }

    override fun onResume() {
        super.onResume()
        viewModel.updateViews()
    }
    override fun getLayoutId(): Int {
        return R.layout.activity_employee
    }

    override fun initViewModel(): EmployeeViewModel {
        return ViewModelProvider(this)[EmployeeViewModel::class.java]
    }
    override fun openLoginScreen() {
        Toast.makeText(this, "you're log out", Toast.LENGTH_LONG)
            .show()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}