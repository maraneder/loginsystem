package com.example.loginsystem

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SignUpActivity : AppCompatActivity() {
    private lateinit var tvEmail:TextView
    private lateinit var tvPassword:TextView
    private lateinit var btnRegister: Button
    private lateinit var tvUsername: TextView

    private lateinit var auth: FirebaseAuth

    private lateinit var sEmail: String
    private lateinit var sPassword: String
    private lateinit var sUsername: String

    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        // Initialize Firebase Auth
        auth = Firebase.auth
        database = Firebase.database.reference

        tvEmail = findViewById(R.id.EmailAdress)
        tvPassword = findViewById(R.id.Password)
        btnRegister = findViewById(R.id.Register)
        tvUsername = findViewById(R.id.Username)


        btnRegister.setOnClickListener{
            sEmail = tvEmail.text.toString().trim()
            sPassword = tvPassword.text.toString().trim()

            auth.createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        auth.currentUser?.sendEmailVerification()
                            ?.addOnSuccessListener {
                                Toast.makeText(this, "Please verify your e-mail", Toast.LENGTH_SHORT).show()
                                saveData()
                            }
                            ?.addOnFailureListener{
                                Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
                            }

                        val user = auth.currentUser
//                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                        updateUI(null)
                    }
                }
        }
    }

    private fun saveData() {
        sEmail = tvEmail.text.toString().trim()
        sUsername = tvUsername.text.toString().trim()


        val user = UserModel(sUsername, sEmail)

        val userId = FirebaseAuth.getInstance().currentUser!!.uid

        database.child("User").child(userId).setValue(user)
    }


    private fun updateUI(user: FirebaseUser?) {
        val intent = Intent(this, DashboardMainActivity::class.java)
        startActivity(intent)

    }


}