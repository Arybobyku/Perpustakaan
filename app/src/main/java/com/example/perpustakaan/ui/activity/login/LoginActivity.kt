package com.example.perpustakaan.ui.activity.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.example.perpustakaan.R
import com.example.perpustakaan.helper.RealmHelper
import com.example.perpustakaan.ui.base.View
import com.example.perpustakaan.ui.activity.dashboard.DashboardActivity
import com.example.perpustakaan.ui.activity.register.RegisterActivity

class LoginActivity : AppCompatActivity(), View, LoginView{

    lateinit var registerTv:TextView
    lateinit var loginBtn:Button
    lateinit var usernameEt:EditText
    lateinit var passwordEt:EditText
    private var presenter:LoginPresenter?=null
    private lateinit var backgroundLayout: RelativeLayout
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenter()
        initView()
        onAttachView()
        setDialog()
        onClick()
    }

    private fun initView(){
        registerTv = findViewById(R.id.register_tv)
        loginBtn = findViewById(R.id.login_btn)
        backgroundLayout = findViewById(R.id.login_layout)
        passwordEt = findViewById(R.id.password_et)
        usernameEt = findViewById(R.id.username_et)
    }

    private fun onClick(){
        registerTv.setOnClickListener {
            val move = Intent(this,RegisterActivity::class.java)
            startActivity(move)
        }
        loginBtn.setOnClickListener {
            val email = usernameEt.text.toString()
            val password = passwordEt.text.toString()
            presenter?.doLogin(email = email ,password = password)
        }
    }

    override fun onStart() {
        if(RealmHelper.getCount()>=1){
            val move= Intent(this, DashboardActivity::class.java)
            move.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(move)
        }
        super.onStart()
    }

    override fun getActivity(): LoginActivity {
        return  this@LoginActivity
    }

    override fun showLoading(show: Boolean) {
        dialog.let {
            if(show){
                it.show()
            }else{
                it.dismiss()
            }
        }
    }

    override fun loginSuccess() {
        val move = Intent(this, DashboardActivity::class.java)
        move.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(move)
    }

    override fun onAttachView() {
        if(presenter==null){
            presenter = LoginPresenter()
        }
        presenter?.onAttach(this)
    }

    override fun onDetachView() {
        presenter?.onDetach()
    }

    private fun setDialog(){
        val screenLoading = layoutInflater.inflate(R.layout.screen_loading,backgroundLayout,false)
        dialog = AlertDialog.Builder(this)
            .setCancelable(false)
            .setView(screenLoading)
            .create()
    }
}