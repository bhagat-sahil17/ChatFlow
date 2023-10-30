package com.example.chatflow

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.firebase.auth.FirebaseAuth


class UserAdapter ( val context : Context , val userlist : ArrayList<User>):
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.user_layout,parent
        , false )
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {



        val curerntUser = userlist[position]
        holder.textName.text = curerntUser.name

        holder.itemView.setOnClickListener(){


            val intent = Intent(context,ChatActivity::class.java)
            intent.putExtra("name", curerntUser.name)
            intent.putExtra("uid", curerntUser.uid )

            context.startActivity(intent)
        }



    }


    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textName = itemView.findViewById<TextView>(R.id.textView)

    }
}

