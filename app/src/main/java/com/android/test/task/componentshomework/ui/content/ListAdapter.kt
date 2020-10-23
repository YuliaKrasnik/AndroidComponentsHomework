package com.android.test.task.componentshomework.ui.content

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.test.task.componentshomework.R

class ListAdapter(private val contactsList: List<String>) :
    RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contactTextView: TextView? = null

        init {
            contactTextView = itemView.findViewById(R.id.tv_contact)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.contactTextView?.text = contactsList[position]
    }

    override fun getItemCount(): Int = contactsList.size

}