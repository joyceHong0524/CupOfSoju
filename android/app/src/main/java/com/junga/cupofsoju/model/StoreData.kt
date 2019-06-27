package com.junga.cupofsoju.model

data class StoreData(val email:String, val password:String, val name:String, val location:String?,
                     val bill : Int, val phone: String, val storeId: String, val image :String?, val menu :String, val size: Int?, val permission_state : Int)