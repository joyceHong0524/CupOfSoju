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


class ProjectValue {

    companion object {


        // It is value of "TYPE"
        val SINGLE = 0
        val GROUP = 1

        //It is value of "permission_state"
        val PERMITTED = 0
        val NOT_PERMITTED = 1

        //It is for billing systme.
        val SINGLE_BILL_SMALL = 0
        val SINGLE_BILL_MEDIUM = 1
        val SINGLE_BILL_LARGE = 2

        val GROUP_BILL_SMALL = 3
        val GROUP_BILL_MEDIUM = 4
        val GROUP_BILL_LARGE = 5

    }
}