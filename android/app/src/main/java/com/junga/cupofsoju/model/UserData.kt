package com.junga.cupofsoju.model

data class UserData(val email:String="",val password:String="",val name:String="",val location:String?="",val phone:String="",
val bill : Int=-1, val type:Int=-1,val monthLeft: Int=0, val todayLeft: Int=0)