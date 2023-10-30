package com.example.chatflow

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth

class MsgAdapter( val context: Context , val msglist : ArrayList<Message>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var itemR = 1
    var itemS = 2



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if ( viewType == 1 ){
            val view : View = LayoutInflater.from(parent.context).inflate(R.layout.receive,parent
                , false )
            return ReceiveViewHolder(view)
        }
        else{
            val view : View = LayoutInflater.from(parent.context).inflate(R.layout.sent,parent
                , false )
            return SentViewHolder(view)
        }
    }

    override fun getItemCount(): Int {
        return msglist.size

    }

    override fun getItemViewType(position: Int): Int {
        val curMsg = msglist[position]
        if ( FirebaseAuth.getInstance().currentUser?.uid ==  curMsg.senderId){
            return itemS
        }
        else{
            return itemR
        }
    }



    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val curMsg = msglist[position]

        if ( holder.javaClass == SentViewHolder::class.java){
            val viewHolder = holder as SentViewHolder

            holder.sentmsg.text = curMsg.message

        }
        else{
            val viewHolder = holder as ReceiveViewHolder
            val curMsg = msglist[position]
            holder.rMsg.text = curMsg.message

        }
    }
    class SentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val sentmsg = itemView.findViewById<TextView>(R.id.sendMsg)

    }

    class ReceiveViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val rMsg = itemView.findViewById<TextView>(R.id.rMsg)

    }



}