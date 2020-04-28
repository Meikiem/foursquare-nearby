package me.meikiem.foursquarevenuesnearby.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */
@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: FourSquareViewModelFactory): ViewModelProvider.Factory
}
