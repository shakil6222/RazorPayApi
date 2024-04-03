package com.example.razorpayapi

data class PostDataModel(
    val name:String?=null,
    val email:String?=null,
    val contact:String?=null,
    val fail_existing:String?=null,
    val gstin:String?=null,
    val notes: Notes? =null
)
