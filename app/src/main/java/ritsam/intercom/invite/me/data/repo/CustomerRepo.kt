package ritsam.intercom.invite.me.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ritsam.intercom.invite.me.data.model.Customer
import ritsam.intercom.invite.me.data.service.CustomerService

/*
 * Repository to fetch and present the data in usable format ( Model Objects)
 * */
class CustomerRepo {
    companion object {
        private const val CUSTOMER_SERVICE_BASE_URL = "https://s3.amazonaws.com/"
    }

    private var customerService: CustomerService
    private var customerResponsiveData = MutableLiveData<List<Customer>?>()

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        customerService = Retrofit.Builder()
            .baseUrl(CUSTOMER_SERVICE_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CustomerService::class.java)
    }

    /**
     * Making network call to get the hosted file
     * **/
    fun fetchCustomers() {
        customerService.downloadCustomerFile()
            ?.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>?,
                    response: Response<ResponseBody?>
                ) {
                    if (response.body() != null) {
                        customerResponsiveData.postValue(extractResponse(response.body()!!))  // using assertion because we have the null check already
                    } else {
                        customerResponsiveData.postValue(null)  // setting value as null when nothing is returned
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {
                    customerResponsiveData.postValue(null) //setting value as null when request fails
                }
            })
    }

    /** Providing LiveData object to be consumed
     * @return LiveData containing list of customers
     * */
    fun getCustomerResponseLiveData(): LiveData<List<Customer>?> {
        return customerResponsiveData
    }

    /** Converting plaintext into List of Customer Objects
     * @param body
     *
     * @return List of Customers
     * */
    private fun extractResponse(body: ResponseBody): List<Customer> {
        val buffer = body.charStream()
        val stringList = buffer.readLines()
        val customerList = ArrayList<Customer>()
        val gson = Gson()
        for (lineItem in stringList) {
            val customer = gson.fromJson(lineItem, Customer::class.java)
            customerList.add(customer)
        }
        return customerList
    }
}