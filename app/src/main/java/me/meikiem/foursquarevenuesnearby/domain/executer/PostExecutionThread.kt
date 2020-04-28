package me.meikiem.foursquarevenuesnearby.domain.executer

import io.reactivex.Scheduler

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/17/20.
 */
interface PostExecutionThread {
    fun scheduler(): Scheduler

}