package com.example.perpustakaan.ui.activity.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import com.example.perpustakaan.R
import com.example.perpustakaan.ui.base.View
import com.example.perpustakaan.ui.activity.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(),View,RegisterView{

    private var presenter: RegisterPresenter?=null
    private lateinit var backgroundLayout: RelativeLayout
    private lateinit var dialog: AlertDialog
    private lateinit var emailEt:EditText
    private lateinit var nameEt:EditText
    private lateinit var passwordEt:EditText
    private lateinit var registerBtn:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        presenter = RegisterPresenter()
        initView()
        onAttachView()
        setDialog()
        onClick()
    }

    private fun initView(){
        emailEt = findViewById(R.id.email_et)
        nameEt = findViewById(R.id.name_et)
        passwordEt = findViewById(R.id.password_et)
        registerBtn = findViewById(R.id.register_btn)
        backgroundLayout = findViewById(R.id.register_layout)
    }

    private fun onClick(){
        registerBtn.setOnClickListener {
            presenter?.doRegister(
                nameEt.text.toString(),
                emailEt.text.toString(),
                passwordEt.text.toString()
            )
        }
    }

    override fun getActivity(): RegisterActivity {
        return this@RegisterActivity
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

    override fun registerSuccess() {
        val move = Intent(this,DashboardActivity::class.java)
        move.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(move)
    }

    override fun onAttachView() {
        if(presenter==null){
            presenter = RegisterPresenter()
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