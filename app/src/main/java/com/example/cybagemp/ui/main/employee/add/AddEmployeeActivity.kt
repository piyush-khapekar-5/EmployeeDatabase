package com.example.cybagemp.ui.main.employee.add

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.example.cybagemp.R
import com.example.cybagemp.di.component.ActivityComponent
import com.example.cybagemp.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_emp_details.*

class AddEmployeeActivity : BaseActivity<AddEmployeeViewModel>() {
    override fun provideLayoutId(): Int = R.layout.activity_emp_details

    companion object {
        const val TAG = "EmpDetailsActivity"
    }

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
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

        btnUpdate.setOnClickListener {   viewModel.createEmployee()     }
    }

}