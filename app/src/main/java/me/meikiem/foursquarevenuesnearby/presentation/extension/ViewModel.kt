package me.meikiem.foursquarevenuesnearby.presentation.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

inline fun <reified VM : ViewModel> Fragment.viewModelProvider(provider: ViewModelProvider.Factory) =
    ViewModelProvider(this, provider)[VM::class.java]

