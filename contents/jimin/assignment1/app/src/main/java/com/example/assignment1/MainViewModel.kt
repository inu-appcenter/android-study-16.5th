package com.example.assignment1

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: ViewModel() {

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users = _users.asStateFlow()


    init {
        loadUsers()
    }

    private fun loadUsers(){
        _users.update {
            mutableListOf(
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11056f1df7cc58f605ce8029a84269fa.jpg", name = "ehsan"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11945081a5b36eebba0679f61dfbd225.jpg", name = "melika"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11458b284947739865cd857e828f1f21.jpg", name = "jax"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen4f59c3a3f9487457d506860bb75e3247.jpg", name = "sara"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen116a503718103ee85aa48038cc85d079.jpg", name = "marta"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gen11670e8d5aee2f5eacc036906962f823.jpg", name = "niki"),
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gena157df185052c72cc24fec571a77cdf0.jpg", name = "john"),
            )
        }
    }

    fun addUsers(){
        _users.update {
            it.plus(
                User(profile = "https://this-person-does-not-exist.com/img/avatar-gena157df185052c72cc24fec571a77cdf0.jpg", name = "yang"),
            )
        }
    }
}