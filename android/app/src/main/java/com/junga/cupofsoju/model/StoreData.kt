package com.junga.cupofsoju.model

data class StoreData(val email:String="", val password:String="", val name:String="", val location:String?=null,
                     val bill : Int=0, val phone: String="", val storeId: String="", val image :String?=null, val menu :String="", val size: Int?=0, val permission_state : Int=0)