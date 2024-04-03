package com.example.razorpayapi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(val context: Context, val userList: List<Item>):
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

   inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val id = view.findViewById<TextView>(R.id.userId_textItem)
        val userName = view.findViewById<TextView>(R.id.name_textItem)
       val email = view.findViewById<TextView>(R.id.email_textItem)
       val contact :TextView = view.findViewById(R.id.contact_textItem)
       val updateButton :TextView = view.findViewById(R.id.update_button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.id.text = userList[position].id
        holder.userName.text = userList[position].name
        holder.email.text = userList[position].email
        holder.contact.text = userList[position].contact

        holder.updateButton.setOnClickListener {
            val intent = Intent(context,CustemerUpdateActivity::class.java)
            intent.putExtra("custemer_Id",userList[position].id)
            context.startActivity(intent)
        }
    }
}