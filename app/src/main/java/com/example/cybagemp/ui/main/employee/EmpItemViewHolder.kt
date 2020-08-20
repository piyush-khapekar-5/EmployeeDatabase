package com.example.cybagemp.ui.main.employee

import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cybagemp.R
import com.example.cybagemp.data.model.Employee
import com.example.cybagemp.di.component.ViewHolderComponent
import com.example.cybagemp.ui.base.BaseItemViewHolder
import com.example.cybagemp.utils.common.GlideHelper
import kotlinx.android.synthetic.main.item_view_employee.view.*

class EmpItemViewHolder(
    parent: ViewGroup,
    private val clickListener: EmpAdapter.ClickListener?
) :
    BaseItemViewHolder<Employee, EmpItemViewModel>(R.layout.item_view_employee, parent),
    View.OnClickListener {


    override fun injectDependencies(viewHolderComponent: ViewHolderComponent) {
        viewHolderComponent.inject(this)
    }

    init {
        if (clickListener != null) {
            itemView.setOnClickListener(this)
        }
    }


    override fun setupObservers() {
        super.setupObservers()
        viewModel.name.observe(this, Observer {
            itemView.tvEmpName.text = "Name: $it"
        })

        viewModel.age.observe(this, Observer {
            itemView.tvEmpAge.text = "Age: $it"
        })

        viewModel.salary.observe(this, Observer {
            itemView.tvEmpSalary.text = "Salary: $it"
        })

        viewModel.empId.observe(this, Observer {
            itemView.contentDescription = it
        })

        itemView.ivProfile.setBackgroundResource(R.drawable.user)
        /*viewModel.profileImage.observe(this, Observer {
            it?.run {
                val glideRequest = Glide
                    .with(itemView.ivProfile.context)
                    .load(GlideHelper.getProtectedUrl(url, headers))
                    .apply(RequestOptions.circleCropTransform())
                    .apply(RequestOptions.placeholderOf(R.drawable.user))

                glideRequest.into(itemView.ivProfile)
            }
        })*/
    }

    override fun setupView(view: View) {
    }

    override fun onClick(v: View?) {
        if (v != null) {
            clickListener?.onItemClick(v, v.contentDescription as String)
        }
    }

}