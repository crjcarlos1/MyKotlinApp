package com.cralos.mykotlinapp2.fragments.mvp.example2.utils

import com.cralos.mykotlinapp2.fragments.mvp.example2.models.User

class UsersUtils {

    companion object {
        @JvmStatic
        fun getAddres(user: User): String {
            return "${user.address.street}, ${user.address.city}"
        }
    }

}