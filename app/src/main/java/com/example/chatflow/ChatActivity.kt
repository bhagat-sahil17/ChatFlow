package com.example.chatflow

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ChatActivity : AppCompatActivity() {
    private lateinit var msgRecyclerView: RecyclerView
    private lateinit var msgBox : EditText
    private lateinit var sendbtn : ImageView
    private lateinit var msgAdapter: MsgAdapter
    private lateinit var msglist : ArrayList<Message>
    private lateinit var mDbRef : DatabaseReference

    var receiverRoom : String? = null
    var senderRoom : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat)



        var name = intent.getStringExtra("name")
        var receiverUid = intent.getStringExtra("uid")


        var senderUid = FirebaseAuth.getInstance().currentUser?.uid
        mDbRef = FirebaseDatabase.getInstance().getReference()
        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid



        supportActionBar?.title = name


        msgRecyclerView = findViewById(R.id.chatRview)
        msgBox = findViewById(R.id.msgBox)
        sendbtn = findViewById(R.id.sendmsg)
        msglist = ArrayList()
        msgAdapter = MsgAdapter(this,msglist)


        msgRecyclerView.layoutManager = LinearLayoutManager(this)
        msgRecyclerView.adapter = msgAdapter





        mDbRef.child("chats").child(senderRoom!!).child("message")
            .addValueEventListener(object : ValueEventListener{
                @SuppressLint("NotifyDataSetChanged")
                override fun onDataChange(snapshot: DataSnapshot) {
                    msglist.clear()

                    for ( i in snapshot.children ) {
                        val msg = i.getValue(Message::class.java)
                        msglist.add(msg!!)

                    }
                    msgAdapter.notifyDataSetChanged()

                }

                override fun onCancelled(error: DatabaseError) {

                }
            })






        sendbtn.setOnClickListener(){


            val message = msgBox.text.toString()
            val messageObject = Message( message , senderUid!! )

            mDbRef.child("chats").child(senderRoom!!).child("message").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("chats").child(receiverRoom!!).child("message").push()
                        .setValue(messageObject)
                }
            msgBox.setText("")

        }




    }
}