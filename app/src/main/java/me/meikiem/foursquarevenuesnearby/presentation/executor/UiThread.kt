package me.meikiem.foursquarevenuesnearby.presentation.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import me.meikiem.foursquarevenuesnearby.domain.executer.PostExecutionThread
import javax.inject.Inject

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

class UiThread @Inject constructor() : PostExecutionThread {
    override fun scheduler(): Scheduler = AndroidSchedulers.mainThread()
}