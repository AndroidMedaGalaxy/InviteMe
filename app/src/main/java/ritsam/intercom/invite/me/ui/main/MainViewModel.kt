package ritsam.intercom.invite.me.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ritsam.intercom.invite.me.data.model.Customer
import ritsam.intercom.invite.me.data.repo.CustomerRepo


class MainViewModel() : ViewModel() {
    var customerRepo: CustomerRepo = CustomerRepo()
    var customerResponseLiveData: LiveData<List<Customer>?>

    init {
        customerRepo.fetchCustomers()
        customerResponseLiveData = customerRepo.getCustomerResponseLiveData();
    }

    fun getCustomerData(): LiveData<List<Customer>?>? {
        return customerResponseLiveData
    }


}