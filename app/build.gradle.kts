import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExtensions)
    id(BuildPlugins.kotlinKapt)
    id(BuildPlugins.NavigationSafeargs)
}

var keystorePropertiesFile = File(rootDir, "keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(keystorePropertiesFile.inputStream())

androidExtensions {
    isExperimental = true
    defaultCacheImplementation = org.jetbrains.kotlin.gradle.internal.CacheImplementation.SPARSE_ARRAY
}

android {

    signingConfigs {
        create("release") {
            storeFile = File(keystorePropertiesFile.parentFile, keystoreProperties["storeFile"] as String)
            storePassword = keystoreProperties["storePassword"] as String
            keyAlias = keystoreProperties["keyAlias"] as String
            keyPassword = keystoreProperties["keyPassword"] as String
        }
    }

    compileSdkVersion(AndroidSdk.compile)
    defaultConfig {
        applicationId = "me.meikiem.foursquarevenuesnearby"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "API_URL", "\"https://api.foursquare.com/v2/\"")
        buildConfigField("String", "CLIENT_ID", "\"XGDQHVEU01DRVWMI0J5IEMO4BG0WKVOI0UOKWD1RZLJRAN3Z\"")
        buildConfigField("String", "CLIENT_SECRET", "\"WPUBJRFTLI0EQBROVR055A2S25UBGCBLRRRRCJHXDSHKEAKJ\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    viewBinding.isEnabled = true

    dataBinding.isEnabled = true
}

// config JVM target to 1.8 for kotlin compilation tasks
tasks.withType < org.jetbrains.kotlin.gradle.tasks.KotlinCompile > ().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.ktxCore)
    implementation(Libraries.roomRunTime)
    implementation(Libraries.legacy)
    kapt(Libraries.roomCompiler)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUI)
    implementation(Libraries.navigationFragmentKtx)
    implementation(Libraries.navigationFragmentUiKtx)
    implementation(Libraries.gson)
    implementation(Libraries.kotlinxCoroutinesCore)
    implementation(Libraries.kotlinxCoroutinesAndroid)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitConverterGson)
    implementation(Libraries.retrofitCoroutineAdapter)
    implementation(Libraries.retrofitAdapterRxJava)
    implementation(Libraries.rxJava)
    implementation(Libraries.rxAndroid)
    implementation(Libraries.okHttpLogingInterceptor)
    implementation(Libraries.okHttp)
    implementation(Libraries.lifecycleExtensions)
    implementation(Libraries.lifecycleViewModel)
    kapt(Libraries.lifecycleCompiler)
    implementation(Libraries.preference)
    implementation(Libraries.dagger)
    kapt(Libraries.daggerCompiler)
    implementation(Libraries.daggerAndroid)
    implementation(Libraries.daggerAndroidSupport)
    kapt(Libraries.daggerAndroidProcessor)
    annotationProcessor(Libraries.annotation)
    testImplementation(TestLibraries.junit4)
    androidTestImplementation(TestLibraries.espresso)
    androidTestImplementation(TestLibraries.testRunner)
    implementation(Libraries.navigationDynamicFeature)
    implementation(Libraries.roomRxJava)
    implementation(Libraries.paging)
    implementation(Libraries.playServiceLocation)
    implementation(Libraries.glide)
}
