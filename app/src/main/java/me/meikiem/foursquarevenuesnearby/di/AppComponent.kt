package me.meikiem.foursquarevenuesnearby.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import me.meikiem.foursquarevenuesnearby.MyApplication
import me.meikiem.foursquarevenuesnearby.di.data.CacheModule
import me.meikiem.foursquarevenuesnearby.di.data.DataModule
import me.meikiem.foursquarevenuesnearby.di.data.NetworkModule
import me.meikiem.foursquarevenuesnearby.di.data.RepositoryModule
import me.meikiem.foursquarevenuesnearby.di.domain.ExecutionModule
import javax.inject.Singleton

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        DataModule::class,
        ExecutionModule::class,
        CacheModule::class,
        AppModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    override fun inject(app: MyApplication)
}
