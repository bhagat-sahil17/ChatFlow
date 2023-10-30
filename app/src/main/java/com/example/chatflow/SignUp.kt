package com.example.chatflow

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {

    private lateinit var ETemail : EditText
    private lateinit var ETpass : EditText
    private lateinit var lbtn : Button
    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)
        supportActionBar?.hide()


        ETemail = findViewById(R.id.emailET)
        ETpass = findViewById(R.id.passET)
        lbtn = findViewById(R.id.loginbtn)

        mAuth = FirebaseAuth.getInstance()


        lbtn.setOnClickListener {
            val email = ETemail.text.toString()
            val pass = ETpass.text.toString()

            login( email , pass )
        }



    }

    private fun login(email: String, password : String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    var i = Intent(this@SignUp,MainScreen::class.java)
                    finish()
                    startActivity(i)
                    // Sign in success, update UI with the signed-in user's information

                }
                else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,"User does not exist or incorrect password entered",Toast.LENGTH_SHORT).show()

                }
            }
    }
}