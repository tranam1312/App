package com.example.app.model

import android.view.View
import androidx.navigation.findNavController
import com.example.app.R
import android.os.Bundle
import android.text.Layout
import androidx.annotation.Keep
import com.example.app.home.HomeFragment
import java.io.Serializable


data class User(var info: Info, var results: List<Results>)
data class Info(var count : Int?, var pages: Int?,var  next : String?, var pev : String?)
@Keep
data class Results(var id : Int?, var name: String ?, var species :String?, var type: String?, var gender: String, var image: String ): Serializable{
}