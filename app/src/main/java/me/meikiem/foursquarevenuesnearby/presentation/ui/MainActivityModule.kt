package me.meikiem.foursquarevenuesnearby.presentation.ui

import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.meikiem.foursquarevenuesnearby.di.PerActivity
import me.meikiem.foursquarevenuesnearby.di.PerFragment
import me.meikiem.foursquarevenuesnearby.presentation.ui.detail.DetailFragment
import me.meikiem.foursquarevenuesnearby.presentation.ui.detail.DetailFragmentModule
import me.meikiem.foursquarevenuesnearby.presentation.ui.explore.ExploreFragment
import me.meikiem.foursquarevenuesnearby.presentation.ui.explore.ExploreFragmentModule

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Module
abstract class MainActivityModule {

    @PerFragment
    @ContributesAndroidInjector(modules = [ExploreFragmentModule::class])
    internal abstract fun exploreFragment(): ExploreFragment

    @PerFragment
    @ContributesAndroidInjector(modules = [DetailFragmentModule::class])
    internal abstract fun detailFragment(): DetailFragment

    @Binds
    @PerActivity
    internal abstract fun mainNavigator(mainActivity: MainActivity): MainNavigator


}