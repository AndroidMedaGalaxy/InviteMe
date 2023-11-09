package ritsam.intercom.invite.me.data.service

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Streaming

/*
* Service to fetch the remote file
* */

interface CustomerService {
    @Streaming // In case we get a large chunk of data, we will be able to protect the memory from overloading as it's processed as is, like a stream
    @GET("/intercom-take-home-test/customers.txt")
    fun downloadCustomerFile(): Call<ResponseBody?>?
}