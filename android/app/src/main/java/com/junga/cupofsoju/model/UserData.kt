package com.junga.cupofsoju.model

data class UserData(val id:String,val password:String,val name:String,val location:String,val phone:String,
val bill : Int, val type:Int,val monthLeft: Int, val todayLeft: Int)