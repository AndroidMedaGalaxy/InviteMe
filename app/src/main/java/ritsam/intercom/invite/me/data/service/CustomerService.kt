package ritsam.intercom.invite.me.data.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url


interface CustomerService {
    @Streaming
    @GET("/intercom-take-home-test/customers.txt")
    open fun downloadCustomerFile(): Call<ResponseBody?>?
}