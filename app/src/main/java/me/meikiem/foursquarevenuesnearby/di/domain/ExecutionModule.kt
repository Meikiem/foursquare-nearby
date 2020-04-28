package me.meikiem.foursquarevenuesnearby.di.domain

import dagger.Binds
import dagger.Module
import me.meikiem.foursquarevenuesnearby.data.executor.JobExecutor
import me.meikiem.foursquarevenuesnearby.domain.executer.PostExecutionThread
import me.meikiem.foursquarevenuesnearby.domain.executer.ThreadExecutor
import me.meikiem.foursquarevenuesnearby.presentation.executor.UiThread

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

@Module
abstract class ExecutionModule {
    @Binds
    abstract fun threadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Binds
    abstract fun postExecutionThread(uiThread: UiThread): PostExecutionThread
}