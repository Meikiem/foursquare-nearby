package me.meikiem.foursquarevenuesnearby.presentation.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (T) -> Unit) {
    liveData.observe(this, Observer { action(it) })
}

fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> = Transformations.map(this, body)

fun <X, Y> LiveData<X>.switchMap(body: (X) -> LiveData<Y>): LiveData<Y> = Transformations.switchMap(this, body)
