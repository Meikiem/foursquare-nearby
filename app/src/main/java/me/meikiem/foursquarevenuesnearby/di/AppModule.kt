package me.meikiem.foursquarevenuesnearby.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.meikiem.foursquarevenuesnearby.presentation.ui.MainActivity
import me.meikiem.foursquarevenuesnearby.presentation.ui.MainActivityModule

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Module
abstract class AppModule{
    @PerActivity
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun mainActivity(): MainActivity

    @Binds
    abstract fun bindContext(application: Application): Context

}