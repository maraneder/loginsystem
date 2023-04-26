package com.example.loginsystem

import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.Date

data class UserModel(
    val username: String? = null,
    val email: String?=null,
    val dateTime: LocalDateTime?=null,
    val timeBlocked: LocalDateTime?=null

)
