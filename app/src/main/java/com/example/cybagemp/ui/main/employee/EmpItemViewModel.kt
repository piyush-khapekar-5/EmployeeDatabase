package com.example.cybagemp.ui.main.employee

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.cybagemp.data.model.Employee
import com.example.cybagemp.data.model.Image
import com.example.cybagemp.data.remote.Networking
import com.example.cybagemp.ui.base.BaseItemViewModel
import com.example.cybagemp.utils.network.NetworkHelper
import com.example.cybagemp.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class EmpItemViewModel @Inject constructor(
    schedulerProvider: SchedulerProvider,
    compositeDisposable: CompositeDisposable,
    networkHelper: NetworkHelper
) : BaseItemViewModel<Employee>(schedulerProvider, compositeDisposable, networkHelper) {

    companion object {
        const val TAG = "EmpItemViewModel"
    }
    private val headers = mapOf(
        Pair(Networking.HEADER_API_KEY, Networking.API_KEY)
    )

    val name: LiveData<String> = Transformations.map(data) { it.employee_name }
    val age: LiveData<String> = Transformations.map(data) { it.employee_age }
    val salary: LiveData<String> = Transformations.map(data) { it.employee_salary }
    val empId: LiveData<String> = Transformations.map(data) { it.id }
    val profileImage: LiveData<Image> = Transformations.map(data) {
        it.profile_image?.run {
            Image(this, headers )
        }
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate() called")
    }

}