const val kotlinVersion = "1.3.61"

object BuildPlugins {

    object Versions {
        const val buildTools = "3.6.1"
        const val gmsService = "4.3.3"
        const val navigation = "1.0.0"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.buildTools}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val gmsGoogleService = "com.google.gms:google-services:${Versions.gmsService}"
    const val navigationGradlePlugin = "android.arch.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinKapt = "kotlin-kapt"
    const val gmsServices = "com.google.gms.google-services"
    const val NavigationSafeargs = "androidx.navigation.safeargs"

}


object AndroidSdk {
    const val min = 21
    const val compile = 29
    const val target = compile
}

object Libraries {
    private object Versions {
        const val jetpack = "1.1.0"
        const val constraintLayout = "1.1.3"
        const val ktx = "1.2.0"
        const val kotlin = "1.3.61"
        const val room = "2.1.0-alpha01"
        const val navigation = "2.3.0-alpha04"
        const val lifecycle = "2.0.0"
        const val retrofit = "2.4.0"
        const val dagger = "2.22"
        const val okhttp = "3.12.0"
        const val legacy = "1.0.0"
        const val lifecycleExtension = "2.2.0"
        const val lifecycleViewModel = "2.2.0"
        const val gson = "2.8.5"
        const val coroutines = "1.3.0"
        const val retrofitCoroutineAdapter = "0.9.2"
        const val rxJava = "2.2.9"
        const val rxAndroid = "2.1.1"
        const val preference = "1.1.0"
        const val material = "1.1.0"
        const val annotation = "1.0"
        const val paging = "2.1.2"
        const val playServiceMap = "17.0.0"
        const val glideVersion = "4.9.0"
    }


    const val kotlinStdLib              = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion"
    const val appCompat                 = "androidx.appcompat:appcompat:${Versions.jetpack}"
    const val constraintLayout          = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val ktxCore                   = "androidx.core:core-ktx:${Versions.ktx}"
    //room
    const val roomRunTime               = "androidx.room:room-runtime:${Versions.room}"
    const val legacy                    = "androidx.legacy:legacy-support-v4:${Versions.legacy}"
    const val roomCompiler              = "androidx.room:room-compiler:${Versions.room}"
    const val roomRxJava              = "androidx.room:room-rxjava2:${Versions.room}"

    // Navigation
    const val navigationFragment        = "androidx.navigation:navigation-fragment:${Versions.navigation}"
    const val navigationUI              = "androidx.navigation:navigation-ui:${Versions.navigation}"
    const val navigationFragmentKtx     = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationFragmentUiKtx   = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val navigationDynamicFeature  = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
    // Gson
    const val gson                      = "com.google.code.gson:gson:${Versions.navigation}"
    // Kotlin Android Coroutines
    const val kotlinxCoroutinesCore     = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val kotlinxCoroutinesAndroid  = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    // Retrofit
    const val retrofit                  = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterGson     = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitCoroutineAdapter  = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutineAdapter}"
    const val retrofitAdapterRxJava     = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    //RxJava
    const val rxJava                    = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val rxAndroid                 = "io.reactivex.rxjava2:rxandroid:${Versions.rxAndroid}"
    //OkHttp
    const val okHttpLogingInterceptor   = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okHttp                    = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    // ViewModel
    const val lifecycleExtensions       = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtension}"
    const val lifecycleViewModel        = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModel}"
    const val lifecycleCompiler         = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleViewModel}"
    // Preference
    const val preference                = "androidx.preference:preference:${Versions.preference}"
    //Dagger
    const val dagger                    = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler            = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val daggerAndroid             = "com.google.dagger:dagger-android:${Versions.dagger}"
    const val daggerAndroidSupport      = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val daggerAndroidProcessor    = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    //Annotation
    const val annotation                = "javax.annotation:jsr250-api:${Versions.annotation}"
    const val paging                    = "androidx.paging:paging-runtime-ktx:${Versions.paging}"
    // Location
    const val playServiceLocation       = "com.google.android.gms:play-services-location:${Versions.playServiceMap}"
    // Glide
    const val glide                     = "com.github.bumptech.glide:glide:${Versions.glideVersion}"

}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.12"
        const val testRunner = "1.1.0-alpha4"
        const val espresso = "3.1.0-alpha4"
    }
    const val junit4     = "junit:junit:${Versions.junit4}"
    const val testRunner = "androidx.test:runner:${Versions.testRunner}"
    const val espresso   = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
