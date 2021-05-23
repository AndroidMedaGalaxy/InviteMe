package ritsam.intercom.invite.me.ui.main

import androidx.lifecycle.*
import ritsam.intercom.invite.me.data.model.Customer
import ritsam.intercom.invite.me.data.model.LatLong
import ritsam.intercom.invite.me.data.repo.CustomerRepo
import ritsam.intercom.invite.me.util.Constants
import ritsam.intercom.invite.me.util.GPSGreatCircleUtil

/*
 * ViewModel for the MainFragment
 * */
class MainViewModel : ViewModel() {
    private var customerRepo: CustomerRepo = CustomerRepo()
    private var customerResponseLiveData: LiveData<List<Customer>?>
    var customerProcessedLiveData = MutableLiveData<List<Customer>?>()

    init {
        customerRepo.fetchCustomers() // network request
        customerResponseLiveData = customerRepo.getCustomerResponseLiveData()
    }

    /**
     * Provides access to the LiveData
     *
     * ViewModel should usually have getters and setters rather than making the variables public
     *
     * @return LiveData containing Customer List
     * **/
    fun getCustomerData(): LiveData<List<Customer>?> {
        return customerResponseLiveData
    }

    /**
     * process the Customer data and populate the list of Customers
     * that are within the specified radius
     *
     * the MutableLiveData is then updated with the processed values
     * @param customerList
     * **/
    fun processCustomers(customerList: List<Customer>) {
        val processCustomerList = ArrayList<Customer>()
        for (customer in customerList)

            if (GPSGreatCircleUtil.calcDistance(
                    Constants.EARTH_RADIUS, LatLong(
                        latitude = customer.latitude.toDouble(),
                        longitude = customer.longitude.toDouble()
                    )
                ) <= Constants.SEARCH_RADIUS   //Search Radius in Km
            ) {
                processCustomerList.add(customer)
            }
       var processedSortedArray =  processCustomerList.sortedWith(compareBy { it.user_id })
        customerProcessedLiveData.postValue(processedSortedArray)
    }
}
