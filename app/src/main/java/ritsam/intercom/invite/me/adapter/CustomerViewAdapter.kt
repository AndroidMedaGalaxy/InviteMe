package ritsam.intercom.invite.me.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ritsam.intercom.invite.me.R
import ritsam.intercom.invite.me.data.model.Customer

/*
* Adapter for recycler view that populates the list
* */
class CustomerViewAdapter(context: Activity?, customerList: List<Customer>): RecyclerView.Adapter<CustomerViewAdapter.ViewHolder>() {
    var context: Activity? = context
    var customerList = customerList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_customer_list, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        customerList[position].let { holder.bindItems(it) }
    }

    override fun getItemCount(): Int {
       return customerList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(customer: Customer) {

            val textUserId = itemView.findViewById(R.id.txt_user_id) as TextView
            val textUserName = itemView.findViewById(R.id.txt_name) as TextView
            textUserId.text = itemView.context.getString(R.string.txt_user_id, customer.user_id) // user Id
            textUserName.text = itemView.context.getString(R.string.txt_name, customer.name) // User name
        }
    }
}