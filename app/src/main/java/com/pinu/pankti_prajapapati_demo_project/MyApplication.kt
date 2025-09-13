package com.pinu.pankti_prajapapati_demo_project

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {


    companion object {
        private var _instance: MyApplication? = null
        val instance: MyApplication = _instance ?: MyApplication()
    }

    override fun onCreate() {
        super.onCreate()
        _instance = this
    }

}