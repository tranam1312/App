package com.example.app.login

import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.app.BR
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import java.lang.Exception


class LoginViewModel: BaseObservable() {
    var errUserName:ObservableField<String> = ObservableField()
    var errPass: ObservableField<String> = ObservableField()
    var startActivity: MutableLiveData<Boolean> = MutableLiveData()
    var isShowLoading: ObservableField<Boolean> = ObservableField()

    @get:Bindable
    var userName : String =""
    set(value) {
        field = value
        notifyPropertyChanged(BR.userName)
    }
    @get:Bindable
    var password : String =""
    set(value) {
        field = value
        notifyPropertyChanged(BR.password)
    }
    fun onClickLogin(view: View) {
        isShowLoading.set(true)
        if (userName.isEmpty()) {
            errUserName.set("Username cannot be left blank")
            if (password.isEmpty()) {
                errPass.set("Pass cannot ba left blank")
            }
        } else if (password.isEmpty()) {
            errPass.set("Pass cannot ba left blank")
        } else {
            if (userName == "admin" && password == "admin") {
                startActivity.value = true
            } else {
                errUserName.set("Username is not corect")
                errPass.set(" Password is not correct")
            }
        }
    }
    fun onCreatAccount( v: View){
         Firebase.auth.createUserWithEmailAndPassword("anhnam2k.yn@gmail.com", "Trannam1234")
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Log.d("hehe", "thành công")
                } else {
                   Log.d("hehee","false")
                }
            }

    }
    fun onClickResetPassword(v:View){
        Firebase.auth.sendPasswordResetEmail("anhnam2k.yn@gmail.com")
            .addOnCompleteListener{task ->
                Log.d("hh", "${task.isSuccessful}")
                if (task.isSuccessful){
                    Log.d("hehe", " thành công")
                }
            }
    }




}