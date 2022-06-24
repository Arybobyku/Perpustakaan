package com.example.perpustakaan.ui.activity.login

import android.widget.Toast
import com.example.perpustakaan.helper.RealmHelper
import com.example.perpustakaan.model.User
import com.example.perpustakaan.service.AuthService
import com.example.perpustakaan.service.UserService
import com.example.perpustakaan.ui.base.Presenter
import java.lang.Exception

class LoginPresenter : Presenter<LoginView> {

    private var view: LoginView? = null

    override fun onAttach(view: LoginView) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

     fun doLogin(email: String, password: String) {
        view?.let {
            it.showLoading(true)
            if (email.isNotEmpty() && password.isNotEmpty()) {
                AuthService.signIn(email,password).addOnSuccessListener {result->
                    UserService.getUserById(result.user?.uid!!).addOnSuccessListener {data->
                        val user = User()
                        user.id = data["id"].toString()
                        user.fullName = data["fullName"].toString()
                        user.email = data["email"].toString()
                        saveMe(user)
                    }.addOnFailureListener {e->
                        it.showLoading(false)
                        it.showMessage(
                            it.getActivity(),
                            e.message.toString(),
                            Toast.LENGTH_LONG
                        )
                    }
                }.addOnFailureListener {e->
                    it.showLoading(false)
                    it.showMessage(
                        it.getActivity(),
                        e.message.toString(),
                        Toast.LENGTH_LONG
                    )
                }

            } else {
                it.showLoading(false)
                it.showMessage(
                    it.getActivity(),
                    "Silahkan masukan username dan paswword terlebih dahulu",
                    Toast.LENGTH_LONG
                )
            }
        }
    }

    private fun saveMe(user: User) {
        try {
            if (RealmHelper.saveMe(user)) {
                view?.showLoading(false)
                view?.loginSuccess()
            }
        } catch (e: Exception) {
            view?.showMessage(view!!.getActivity(), e.message.toString(), Toast.LENGTH_LONG)
        }
    }
}