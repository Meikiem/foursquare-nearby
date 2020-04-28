package me.meikiem.foursquarevenuesnearby.presentation.ui.explore

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.meikiem.foursquarevenuesnearby.di.ViewModelKey

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Module
internal abstract class ExploreFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(ExploreFragmentViewModel::class)
    abstract fun exploreFragmentViewModel(fragmentViewModel: ExploreFragmentViewModel): ViewModel
}