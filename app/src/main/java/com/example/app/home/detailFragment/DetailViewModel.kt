package com.example.app.home.detailFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.app.model.Results

class DetailViewModel : ViewModel() {
    private var resultLiveda :Results ?= null
    fun setResult(results: Results){
        resultLiveda=results
    }
    fun getName(): String{
        return "Name: ${resultLiveda?.name.toString()}"
    }
    fun getSpecies() : String {
        return "Species: ${resultLiveda?.species.toString()}"
    }
    fun getGender(): String {
        return "Gender: ${resultLiveda?.gender.toString()}"
    }
    fun getType ():String {
        return "Type: ${ resultLiveda?.type.toString()}"
    }
    fun getImage(): String{
        return resultLiveda?.image.toString()
    }
}