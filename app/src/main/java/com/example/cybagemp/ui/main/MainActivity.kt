package com.example.cybagemp.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cybagemp.R
import com.example.cybagemp.di.component.ActivityComponent
import com.example.cybagemp.ui.base.BaseActivity
import com.example.cybagemp.ui.main.employee.add.AddEmployeeActivity
import com.example.cybagemp.ui.main.employee.EmpAdapter
import com.example.cybagemp.ui.main.employee.details.EmpDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>() {

    companion object {
        const val TAG = "MainActivity"
    }

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var employeeAdapter: EmpAdapter

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupObservers() {
        super.setupObservers()
        viewModel.addEmpNavigation.observe(this, Observer {
            it.getIfNotHandled()?.run { showAddEmp() }
        })

        viewModel.loading.observe(this, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })

        viewModel.employees.observe(this, Observer {
            Log.d(TAG, "Emp list::" + it.data!!.get(0).employee_name)
            it.data?.run { employeeAdapter.appendData(this) }
        })

    }

    /**
    Navigates to AddEmployeeActivity
     */
    private fun showAddEmp() {
        startActivity(Intent(applicationContext, AddEmployeeActivity::class.java))
    }

    override fun setupView(savedInstanceState: Bundle?) {
        bottomNavigation.run {
            itemIconTintList = null
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.itemAddEmp -> {
                        viewModel.onAddEmpSelected()
                        true
                    }
                    else -> false
                }
            }
        }
        rvEmployees.apply {
            layoutManager = linearLayoutManager
            adapter = employeeAdapter
        }

        employeeAdapter.setOnItemClickListener(object : EmpAdapter.ClickListener {
            override fun onItemClick(v: View, id: String) {
                Toast.makeText(
                    this@MainActivity,
                    "Clicked: ${id}",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(applicationContext, EmpDetailsActivity::class.java)
                intent.putExtra("Employee_ID", id)
                startActivity(intent)
            }
        })
        // Added divider to recucvler view
        rvEmployees.addItemDecoration(
            DividerItemDecoration(
                application,
                DividerItemDecoration.VERTICAL
            )
        )
    }

}