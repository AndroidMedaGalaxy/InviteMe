package ritsam.intercom.invite.me.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ritsam.intercom.invite.me.R
import ritsam.intercom.invite.me.adapter.CustomerViewAdapter
import ritsam.intercom.invite.me.data.model.Customer
import ritsam.intercom.invite.me.databinding.MainFragmentBinding
import ritsam.intercom.invite.me.util.Constants

class MainFragment : Fragment() {
    /*
     *
     * MainFragment is the View where we will be displaying the list of all the
     * valid customers within radius
    * */
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var linearLayoutManager: LinearLayoutManager

    private lateinit var customerAdapter: CustomerViewAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layoutInflater.inflate(R.layout.main_fragment, container, false)
        binding =
            MainFragmentBinding.inflate(layoutInflater)  // using ViewBinding to bind the data to views

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        linearLayoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = linearLayoutManager

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        //observing the data that's returned by the repo
        viewModel.getCustomerData().observe(viewLifecycleOwner, { customerList ->
            if (customerList != null) {
                viewModel.processCustomers(customerList) // sending the data to viewmodel for processing
            }
            else binding.message.text = getString(R.string.error_text)
        })

        // observing processed Live data
        viewModel.customerProcessedLiveData.observe(viewLifecycleOwner, { customerList ->
            binding.message.text = getString(R.string.top_message, customerList?.size, Constants.SEARCH_RADIUS)
            if (customerList != null) {
                for(customer in customerList)  // IMPORTANT:  Only for the purpose of Loggin the output. Strictly no loggin in Production APp
                {

                    logOutput(customer)
                }
                customerAdapter = CustomerViewAdapter(requireActivity(), customerList)
                binding.recyclerView.adapter = customerAdapter
            }
        })
    }

    /**
     * STRICTLY FOR ASSIGNMENT PURPOSE ONLY
     * YOU SHOULD NOT LOG ANY DATA IN A PRODUCTION APPLICATION
     *
     * As Jake Wharton (Retrofit 2.0 Author) Says,
     * Everytime you log into production App, one puppy dies
     *
     * @param customer
     * */
    private fun logOutput(customer: Customer) {
        Log.i("TAG",getString(R.string.txt_user_id, customer.user_id) +" " + getString(R.string.txt_name, customer.name) // User name
            // User name
        )
    }
}