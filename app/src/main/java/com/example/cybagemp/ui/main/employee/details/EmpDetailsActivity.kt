package com.example.cybagemp.ui.main.employee.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.Observer
import com.example.cybagemp.R
import com.example.cybagemp.di.component.ActivityComponent
import com.example.cybagemp.ui.base.BaseActivity
import com.example.cybagemp.utils.common.Toaster
import kotlinx.android.synthetic.main.activity_emp_details.*
import kotlinx.android.synthetic.main.activity_main.progressBar

class EmpDetailsActivity : BaseActivity<EmpDetailsViewModel>() {

    companion object {
        const val TAG = "EmpDetailsActivity"
    }

    lateinit var empId: String
    
    override fun provideLayoutId(): Int = R.layout.activity_emp_details

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    @SuppressLint("SetTextI18n")
    override fun setupObservers() {
        super.setupObservers()
        viewModel.loading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.employeeDetails.observe(this, Observer {
            etEmpAge.setText("Age: ${it.data!!.employee_age}")
            etEmpName.setText("Name: ${it.data!!.employee_name}")
            etEmpSalary.setText("Salary: ${it.data!!.employee_salary}")
            ivEmpProfile.setBackgroundResource(R.drawable.user)
        })

        viewModel.launchMain.observe(this, Observer {
            Toaster.show(applicationContext, "Details Updated Successfully!")
            finish()
        })
    }

    override fun setupView(savedInstanceState: Bundle?) {
        empId =  getIntent().getStringExtra("Employee_ID") as String
        Log.d(TAG, empId)
        viewModel.loadEmployeeDetails(empId)

        etEmpName.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onNameChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        etEmpAge.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onAgeChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        etEmpSalary.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.onSalaryChange(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        })

        btnUpdate.setOnClickListener {   viewModel.updateEmployeeDetails(empId)     }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.emp_main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.delete -> {
                viewModel.deleteEmployee(empId)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}