package com.example.loginsystem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.*

class DashboardMainActivity : AppCompatActivity() {
    private lateinit var btnLogOut : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_main)

        btnLogOut = findViewById(R.id.btn_signout)

        btnLogOut.setOnClickListener{
            Firebase.auth.signOut()

            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
        }
    }


}