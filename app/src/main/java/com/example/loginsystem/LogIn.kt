package com.example.loginsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LogIn : AppCompatActivity() {
    private lateinit var btnSignUp: Button
    private lateinit var btnLogin: Button
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        // Initialize Firebase Auth
        auth = Firebase.auth

        btnLogin = findViewById(R.id.btnlogin)
        etEmail = findViewById(R.id.login_email)
        etPassword = findViewById(R.id.login_password)

        btnLogin.setOnClickListener {

            val sEmail = etEmail.text.toString().trim()
            val sPassword = etPassword.text.toString().trim()

            auth.signInWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val user = auth.currentUser
                        updateUI()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
//                        updateUI(null)
                    }
                }



        }




        btnSignUp = findViewById(R.id.btnsignup)
        btnSignUp.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

    }

    private fun updateUI() {
        val intent = Intent(this, DashboardMainActivity::class.java)
        startActivity(intent)

    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUI()
        }
    }
}
