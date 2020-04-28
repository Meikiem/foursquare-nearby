package me.meikiem.foursquarevenuesnearby.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity
import me.meikiem.foursquarevenuesnearby.R
import me.meikiem.foursquarevenuesnearby.databinding.ActivityMainBinding
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject

class MainActivity : DaggerAppCompatActivity(), MainNavigator {

    private lateinit var binding: ActivityMainBinding

    private lateinit var navController: NavController


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onVenueClicked(item: VenueObject) {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.details_prompt))
            .setPositiveButton(getString(R.string.yes)) { dialog, _ ->
                findNavController(R.id.nav_host_main).navigate(
                    R.id.action_exploreFragment_to_detailFragment,
                    Bundle().apply { putString("id", item.id) }
                )
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.no)) { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

    override fun retry() {
    }

    override fun onBack() {
        findNavController(R.id.nav_host_main).navigateUp()
    }

}
