package com.example.app.model

import android.view.View
import androidx.navigation.findNavController
import com.example.app.R
import android.os.Bundle
import android.text.Layout
import androidx.annotation.Keep
import com.example.app.home.HomeFragment
import java.io.Serializable


data class User(val info: Info, val results: List<Results>)
data class Info(val count : Int?, val pages: Int?,val  next : String?, val pev : String?)
@Keep
data class Results(val id : Int?, val name: String ?, val species :String?, val type: String?, val gender: String, val image: String ): Serializable{
}