package com.mahim.mkcam

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MkCamApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Initialize things if needed
    }
}
