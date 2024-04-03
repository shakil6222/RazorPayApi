package com.example.razorpayapi

data class UserResponceListModel(
    val entity:String?=null,
    val count:Int?=null,
    val items:List<Item>?=null
)

data class Item(
    val id :String?=null,
    val entity :String?=null,
    val name :String?=null,
    val email :String?=null,
    val contact :String?=null
)
