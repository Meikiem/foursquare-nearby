package me.meikiem.foursquarevenuesnearby.presentation.ui.detail

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import me.meikiem.foursquarevenuesnearby.di.ViewModelKey

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */


@Module
internal abstract class DetailFragmentModule {
    @Binds
    @IntoMap
    @ViewModelKey(DetailFragmentViewModel::class)
    abstract fun detailFragmentViewModel(fragmentViewModel: DetailFragmentViewModel): ViewModel
}