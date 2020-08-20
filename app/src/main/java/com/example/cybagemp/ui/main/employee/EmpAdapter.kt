package com.example.cybagemp.ui.main.employee

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import com.example.cybagemp.data.model.Employee
import com.example.cybagemp.ui.base.BaseAdapter


class EmpAdapter(
    parentLifecycle: Lifecycle,
    employees: ArrayList<Employee>
) : BaseAdapter<Employee, EmpItemViewHolder>(parentLifecycle, employees) {

    var clickListener: ClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        EmpItemViewHolder(parent, clickListener)

    fun setOnItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

    interface ClickListener {
        fun onItemClick(v: View, id: String)
    }
}