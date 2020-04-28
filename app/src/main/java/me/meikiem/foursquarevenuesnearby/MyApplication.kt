package me.meikiem.foursquarevenuesnearby

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import me.meikiem.foursquarevenuesnearby.di.DaggerAppComponent


/**
 * Created by mohammad.hossein.safari.langaroudi on 4/17/20.
 */
class MyApplication : DaggerApplication() {


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }


}