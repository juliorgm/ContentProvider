package com.example.contatossqlites.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Contact(val id:Int,var name:String,var phoneNumber:String): Parcelable{

}