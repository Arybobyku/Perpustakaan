package com.example.perpustakaan.ui.activity.register

import android.widget.Toast
import com.example.perpustakaan.helper.RealmHelper
import com.example.perpustakaan.model.User
import com.example.perpustakaan.service.AuthService
import com.example.perpustakaan.service.UserService
import com.example.perpustakaan.ui.base.Presenter
import java.lang.Exception

class RegisterPresenter : Presenter<RegisterView> {

    private var view: RegisterView? = null

    override fun onAttach(view: RegisterView) {
        this.view = view
    }

    override fun onDetach() {
        this.view = null
    }

    fun doRegister(
        fullname: String,
        email: String,
        password: String,
    ) {
        view?.let {
            it.showLoading(true)
            if (
                fullname.isNotEmpty() &&
                password.isNotEmpty() &&
                email.isNotEmpty()
            ) {
                AuthService.signUp(email, password)
                    .addOnSuccessListener { result ->
                        val user = User()
                        user.id = result.user?.uid!!
                        user.fullName = fullname
                        user.email = email
                        UserService.setUser(user).addOnSuccessListener {
                            saveMe(user)
                        }.addOnFailureListener { e ->
                            it.showLoading(false)
                            it.showMessage(
                                it.getActivity(),
                                e.toString(),
                                Toast.LENGTH_LONG
                            )
                        }
                    }
                    .addOnFailureListener { e ->
                        it.showLoading(false)
                        it.showMessage(
                            it.getActivity(),
                            e.toString(),
                            Toast.LENGTH_LONG
                        )
                    }
            } else {
                it.showLoading(false)
                it.showMessage(
                    it.getActivity(),
                    "Silahkan isi seluruh form sebelum mendaftar",
                    Toast.LENGTH_LONG
                )
            }
        }
    }

    private fun saveMe(user: User) {
        try {
            if (RealmHelper.saveMe(user)) {
                view?.showLoading(false)
                view?.registerSuccess()
            }
        } catch (e: Exception) {
            view?.showMessage(view!!.getActivity(), e.message.toString(), Toast.LENGTH_LONG)
        }
    }

}