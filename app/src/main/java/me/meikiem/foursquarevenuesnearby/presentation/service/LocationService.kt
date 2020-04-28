package me.meikiem.foursquarevenuesnearby.presentation.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.*
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.location.*
import kotlinx.coroutines.SupervisorJob
import me.meikiem.foursquarevenuesnearby.R
import me.meikiem.foursquarevenuesnearby.presentation.ui.MainActivity

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/26/20.
 */


class LocationService : Service() {

    private val TAG = LocationService::class.java.simpleName
    private val NOTIFICATION_CHANNEL_ID = "channel_01"
    private val NOTIFICATION_ID = 1101
    private val EXTRA_STARTED_FROM_NOTIFICATION = "started_from_notification"

    private val binder = LocalBinder()
    private lateinit var locationRequest: LocationRequest
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationCallback: LocationCallback
    private lateinit var serviceHandler: Handler
    private lateinit var location: Location
    private lateinit var notificationManager: NotificationManager

    private val job = SupervisorJob()

    override fun onCreate() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                onNewLocation(locationResult!!.lastLocation)
            }
        }

        createLocationRequest()
        getLastLocation()

        val handlerThread = HandlerThread(TAG)
        handlerThread.start()
        serviceHandler = Handler(handlerThread.looper)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.app_name)
            val mChannel = NotificationChannel(NOTIFICATION_CHANNEL_ID, name, NotificationManager.IMPORTANCE_DEFAULT)

            notificationManager.createNotificationChannel(mChannel)
        }
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val startedFromNotification = intent.getBooleanExtra(
            EXTRA_STARTED_FROM_NOTIFICATION,
            false
        )

        if (startedFromNotification) {
            removeLocationUpdates()
            stopSelf()
        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        stopForeground(true)
        return binder
    }

    override fun onRebind(intent: Intent) {
        stopForeground(true)
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent): Boolean {
        startForeground(NOTIFICATION_ID, getNotification())
        return true // Ensures onRebind() is called when a client re-binds.
    }

    override fun onDestroy() {
        serviceHandler.removeCallbacksAndMessages(null)
        job.cancel()
    }

    fun requestLocationUpdates() {
        startService(Intent(applicationContext, LocationService::class.java))
        try {
            fusedLocationClient.requestLocationUpdates(
                locationRequest, locationCallback, Looper.myLooper()
            )
        } catch (unlikely: SecurityException) {
            Log.d(TAG, unlikely.toString())
        }
    }

    fun removeLocationUpdates() {
        try {
            fusedLocationClient.removeLocationUpdates(locationCallback)
            stopSelf()
        } catch (unlikely: SecurityException) {
            Log.d(TAG, unlikely.toString())
        }
    }

    private fun getLastLocation() {
        try {
            fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful && task.result != null) {
                    location = task.result!!
                } else {
                }
            }
        } catch (unlikely: SecurityException) {
            Log.d(TAG, unlikely.toString())
        }
    }

    private fun onNewLocation(location: Location) {
        this.location = location
        val intent = Intent(ACTION_BROADCAST)

        val latLng = "${roundFormat(location.latitude)},${roundFormat(location.longitude)}"
        intent.putExtra(NEW_LOCATION, latLng)
        LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)

        if (serviceIsRunningInForeground(this)) {
            notificationManager.notify(NOTIFICATION_ID, getNotification())
        }
    }

    private fun roundFormat(ll:Double):Double{
        val number3digits:Double = Math.round(ll * 1000.0) / 1000.0
        val number2digits:Double = Math.round(number3digits * 100.0) / 100.0
        val solution:Double = Math.round(number2digits * 10.0) / 10.0
        return solution
    }

    private fun createLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.smallestDisplacement = 100F // 100 meters
        locationRequest.interval = 60000
        locationRequest.fastestInterval = 60000 / 2
    }

    inner class LocalBinder : Binder() {
        internal val service: LocationService
            get() = this@LocationService
    }

    @SuppressWarnings("deprecation")
    private fun serviceIsRunningInForeground(context: Context): Boolean {
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (javaClass.name == service.service.className) {
                if (service.foreground) {
                    return true
                }
            }
        }
        return false
    }

    @SuppressWarnings("deprecation")
    private fun getNotification(): Notification {
        val intent = Intent(this, LocationService::class.java)

        intent.putExtra(EXTRA_STARTED_FROM_NOTIFICATION, true)

        val servicePendingIntent = PendingIntent.getService(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val activityPendingIntent = PendingIntent.getActivity(
            this, 0, Intent(this, MainActivity::class.java), 0
        )

        val builder = NotificationCompat.Builder(this)
            .addAction(0, getString(R.string.notification_action_launch), activityPendingIntent)
            .addAction(0, getString(R.string.notification_action_stop), servicePendingIntent)
            .setContentTitle(getString(R.string.notification_content_title))
            .setContentText(getString(R.string.notification_content_text))
            .setOngoing(true)
            .setPriority(1) // Notification.PRIORITY_HIGH
            .setSmallIcon(R.mipmap.ic_launcher)
            .setWhen(System.currentTimeMillis())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(NOTIFICATION_CHANNEL_ID) // Channel ID
        }

        return builder.build()
    }

    companion object {
        const val NEW_LOCATION = "location"
        const val ACTION_BROADCAST = "broadcast"
    }
}
