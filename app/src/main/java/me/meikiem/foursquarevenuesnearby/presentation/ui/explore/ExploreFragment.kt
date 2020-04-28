package me.meikiem.foursquarevenuesnearby.presentation.ui.explore

import android.Manifest
import android.content.*
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.os.IBinder
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.explore_fragment.*
import me.meikiem.foursquarevenuesnearby.R
import me.meikiem.foursquarevenuesnearby.databinding.ExploreFragmentBinding
import me.meikiem.foursquarevenuesnearby.presentation.extension.observe
import me.meikiem.foursquarevenuesnearby.presentation.extension.setSwipeRefreshColors
import me.meikiem.foursquarevenuesnearby.presentation.extension.showSnackbar
import me.meikiem.foursquarevenuesnearby.presentation.extension.viewModelProvider
import me.meikiem.foursquarevenuesnearby.presentation.service.LocationService
import me.meikiem.foursquarevenuesnearby.presentation.ui.MainNavigator
import me.meikiem.foursquarevenuesnearby.result.Result
import me.meikiem.foursquarevenuesnearby.util.ConnectivityUtil
import javax.inject.Inject

class ExploreFragment : DaggerFragment() {

    @Inject
    lateinit var navigator: MainNavigator

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: ExploreFragmentViewModel

    private lateinit var binding: ExploreFragmentBinding

    private lateinit var venuesAdapter: VenuesAdapter

    private val REQUEST_PERMISSIONS_REQUEST_CODE = 34

    private lateinit var locationReceiver: LocationReceiver

    private var locationService: LocationService? = null

    private var serviceBound = false


    private val serviceConnection = object : ServiceConnection {

        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as LocationService.LocalBinder
            locationService = binder.service
            serviceBound = true
        }

        override fun onServiceDisconnected(name: ComponentName) {
            locationService = null
            serviceBound = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(factory)
        viewModel.connectivityAvailable = ConnectivityUtil.isInternetAvailable(context!!)
        locationReceiver = LocationReceiver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.connectivityAvailable = ConnectivityUtil.isInternetAvailable(requireContext())
        binding = ExploreFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()

        if(viewModel.isVenuesInitialized()){
            observe(viewModel.venues){
                venuesAdapter.submitList(it)
            }
        }
    }


    private fun initUI() {
        venuesAdapter = VenuesAdapter { navigator.onVenueClicked(it) }
        with(list) {
            layoutManager = GridLayoutManager(
                requireContext(), 1
            )
            adapter = venuesAdapter
        }

        with(swipeLayout) {
            setSwipeRefreshColors(resources.getIntArray(R.array.swipe_refresh))
        }
    }


    override fun onStart() {
        super.onStart()

        binding.trackingText.setOnClickListener {
            if (binding.trackingText.text == getString(R.string.stop_tracking)) {
                locationService?.removeLocationUpdates()
                binding.trackingText.text = getString(R.string.start_tracking)
                swipeLayout.isRefreshing = false
            } else {
                viewModel.connectivityAvailable = ConnectivityUtil.isInternetAvailable(requireContext())
                if(!isLocationEnabled(requireContext())){
                    binding.main.showSnackbar(getString(R.string.location_permission), Snackbar.LENGTH_INDEFINITE, getString(R.string.ok)){
                        startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                    }
                    return@setOnClickListener
                }
                if (!checkPermissions()) {
                    requestPermissions()
                } else {
                    locationService?.requestLocationUpdates()
                }
                binding.trackingText.text = getString(R.string.stop_tracking)
                swipeLayout.isRefreshing = true

            }
        }

        requireActivity().bindService(
            Intent(context, LocationService::class.java),
            serviceConnection,
            Context.BIND_AUTO_CREATE
        )
    }

    private fun isLocationEnabled(mContext: Context): Boolean {
        val lm = mContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return lm.isProviderEnabled(LocationManager.GPS_PROVIDER) || lm.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER)
    }


    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(
            locationReceiver,
            IntentFilter(LocationService.ACTION_BROADCAST)
        )
    }

    override fun onStop() {
        if (serviceBound) {
            requireActivity().unbindService(serviceConnection)
            serviceBound = false
        }
        super.onStop()
    }

    override fun onPause() {
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(locationReceiver)
        super.onPause()
    }

    private fun checkPermissions(): Boolean {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            requireActivity(),
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (shouldProvideRationale) {
            binding.main.showSnackbar(getString(R.string.location_permission), Snackbar.LENGTH_INDEFINITE, getString(R.string.ok)){
                    requestPermissions(
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_PERMISSIONS_REQUEST_CODE
                    )
            }
        } else {
            requestPermissions(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSIONS_REQUEST_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            when {
                grantResults.isEmpty() -> {
                    locationService?.requestLocationUpdates()
                }
                grantResults[0] == PackageManager.PERMISSION_GRANTED -> {
                    locationService?.requestLocationUpdates()
                }
                else -> // Permission denied.
                    binding.main.showSnackbar(
                        getString(R.string.location_permission_denied_text),
                        Snackbar.LENGTH_INDEFINITE
                    )
            }
        }
    }

    private inner class LocationReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val latLng = intent.getStringExtra(LocationService.NEW_LOCATION)
            if(latLng != null) {
                viewModel.setLocation(latLng)

                observe(viewModel.isCachedAndNotExpired(latLng)){
                    when (it) {
                        is Result.Success -> {
                            swipeLayout.isRefreshing = false
                            viewModel.search(latLng, it.data)
                            observe(viewModel.venues){
                                venuesAdapter.submitList(it)
                            }
                        }
                        is Result.Loading -> {
                            swipeLayout.isRefreshing = true
                        }
                        is Result.Error -> {
                            swipeLayout.isRefreshing = false
                        }
                    }
                }

            }
        }
    }
}
