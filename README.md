# foursquare-nearby
An android app that uses Foursquare public API with MVVM + Clean Architecture + Room + Paging + ViewBinding

# Introduction
The app lets user gets a list of Venues according to his location that will be traced by location service. User could start and stop location tracing and by each item click, some details about the place will be shown like an image, rate, and address of the place.

# Development Environment
This app is written entirely in Kotlin by using the Gradle build system with no unofficial libraries.

# Architecture
Development is based on the recommendations laid out in the [Guide to App Architecture](https://developer.android.com/jetpack/docs/guide).

I used [Dagger2.2](https://dagger.dev/), [RxJava3](https://dagger.dev/), [Retrofit2](https://square.github.io/retrofit/), [Paging](https://developer.android.com/topic/libraries/architecture/paging), [ViewBinging](https://developer.android.com/topic/libraries/data-binding/architecture), [Room Database](https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase)
