package com.mouse.numberfact

import android.app.Application
import com.mouse.numberfact.data.database.NumberFactDao
import com.mouse.numberfact.data.database.NumberFactDatabaseUtil
import com.mouse.numberfact.domain.NumberApi
import com.mouse.numberfact.domain.RetrofitUtil

class NumberFactApp : Application() {

    lateinit var numberApi: NumberApi
        private set
    lateinit var dao: NumberFactDao
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this
        numberApi = RetrofitUtil.init().create(NumberApi::class.java)
        NumberFactDatabaseUtil.init(this)
        dao = NumberFactDatabaseUtil.dao
    }

    companion object {
        lateinit var instance: NumberFactApp
            private set
    }
}