package be.bnp.katas.tictactoe

import android.app.Application

class App : Application(), AppInitKoinDelegate by AppInitKoinDelegate.create() {

    override fun onCreate() {
        super.onCreate()
        initKoinDelegated()
    }
}