package com.junga.cupofsoju.model

//Copyright 2019 Hanjanha Jo
//
//
//Licensed under the Apache License, Version 2.0 (the "License"); you may not use this
//file except in compliance with the License. You may obtain a copy of the License at
//http://www.apache.org/licenses/LICENSE-2.0
//
//Unless required by applicable law or agreed to in writing, software distributed
//under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
//express or implied. See the License for the specific language governing permissions and limitations under the License.
//


data class StoreData(val email:String="", val password:String="", val name:String="", val location:String?=null,
                     val bill : Int=0, val phone: String="", val storeId: String="", val image :String?=null, val menu :String="", val size: Int?=0, val permission_state : Int=0
,val soju_count : Int =0)