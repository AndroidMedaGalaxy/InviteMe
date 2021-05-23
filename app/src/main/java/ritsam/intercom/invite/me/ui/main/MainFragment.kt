package ritsam.intercom.invite.me.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ritsam.intercom.invite.me.R
import ritsam.intercom.invite.me.databinding.MainFragmentBinding

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        layoutInflater.inflate(R.layout.main_fragment, container, false)
        binding = MainFragmentBinding.inflate(layoutInflater)  // using ViewBinding to bind the data to views
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)


        //observing the data that's returned by the repo
        viewModel.getCustomerData().observe(viewLifecycleOwner, { customerList ->
            if (customerList != null) {
                viewModel.processCustomers(customerList) // sending the data to viewmodel for processing
            }
        })

        // observing processed Live data
        viewModel.customerProcessedLiveData.observe(viewLifecycleOwner, { customerList ->
            var displayText = ""
            if (customerList != null) {
                for (customer in customerList) {
                    displayText += customer.name
                    Log.i("Fragment", "${customer.user_id}")
                }
            }
            binding.message.text = displayText
        })
    }
}