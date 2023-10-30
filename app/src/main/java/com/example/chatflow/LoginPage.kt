package com.example.chatflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginPage : AppCompatActivity() {

    private lateinit var ETname : EditText
    private lateinit var ETemail : EditText
    private lateinit var ETpass : EditText
    private lateinit var signBTN : Button
    private lateinit var loginBTN : Button
    private lateinit var mAuth : FirebaseAuth
    private lateinit var mDbref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        supportActionBar?.hide()

        ETname = findViewById(R.id.name)
        ETemail = findViewById(R.id.email)
        ETpass = findViewById(R.id.pw)
        signBTN = findViewById(R.id.button)
        loginBTN = findViewById(R.id.button2)
        mAuth = FirebaseAuth.getInstance()



        signBTN.setOnClickListener {
            var name = ETname.text.toString()
            val email = ETemail.text.toString()
            val pass = ETpass.text.toString()

            signup( name , email , pass )
        }


        loginBTN.setOnClickListener {
            var i = Intent(this,SignUp::class.java)
            finish()
            startActivity(i)
        }



    }

    private fun signup(name : String , email: String , password : String) {



        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase( name , email , mAuth.currentUser?.uid!! )

                    var i = Intent(this,MainScreen::class.java)
                    finish()
                    startActivity(i)
                    // Sign in success, update UI with the signed-in user's information

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, "Authentication failed.", Toast.LENGTH_SHORT,).show()

                }
            }

    }
    
    private fun addUserToDatabase( name: String , email: String , uid : String )
    {
        mDbref = FirebaseDatabase.getInstance().getReference()
        mDbref.child("user").child(uid).setValue(User( name, email, uid ))

    }


}