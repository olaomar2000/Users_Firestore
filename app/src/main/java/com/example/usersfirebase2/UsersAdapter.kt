package com.example.ourproject.Adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.ourproject.modle.Users
import com.example.usersfirebase2.R


class UsersAdapter(var activity: Context?, var data: MutableList<Users>): RecyclerView.Adapter<UsersAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName: TextView = itemView.findViewById(R.id.user_Name)
        val tvNumber: TextView = itemView.findViewById(R.id.user_Number)
        val tvAddress: TextView = itemView.findViewById(R.id.user_Address)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.MyViewHolder {
        var View: View = LayoutInflater.from(activity).inflate(R.layout.user_item,parent,false)
        val myHolder:MyViewHolder = MyViewHolder(View)
        return myHolder
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvName.text = data[position].name
        holder.tvNumber.text = data[position].Number
        holder.tvAddress.text = data[position].address

    }

}
