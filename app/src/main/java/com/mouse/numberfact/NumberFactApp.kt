package com.mouse.numberfact

import android.app.Application
import com.mouse.numberfact.domain.NumberApi
import com.mouse.numberfact.domain.RetrofitUtil

class NumberFactApp : Application() {

    lateinit var numberApi: NumberApi
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        numberApi = RetrofitUtil.init().create(NumberApi::class.java)
    }

    companion object {
        lateinit var instance: NumberFactApp
            private set
    }
}