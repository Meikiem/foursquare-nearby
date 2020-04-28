package me.meikiem.foursquarevenuesnearby.presentation.ui.detail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.detail_fragment.*
import me.meikiem.foursquarevenuesnearby.R
import me.meikiem.foursquarevenuesnearby.databinding.DetailFragmentBinding
import me.meikiem.foursquarevenuesnearby.presentation.extension.hide
import me.meikiem.foursquarevenuesnearby.presentation.extension.viewModelProvider
import me.meikiem.foursquarevenuesnearby.presentation.ui.MainNavigator
import me.meikiem.foursquarevenuesnearby.util.ConnectivityUtil
import javax.inject.Inject

class DetailFragment : DaggerFragment() {

    @Inject
    lateinit var navigator: MainNavigator

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private lateinit var viewModel: DetailFragmentViewModel

    private lateinit var binding: DetailFragmentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = viewModelProvider(factory)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel.connectivityAvailable = ConnectivityUtil.isInternetAvailable(requireContext())
        binding = DetailFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        arguments?.let { viewModel.setVenueId(it["id"] as String) }

        if (viewModel.venueId.value != null)
            viewModel.fetchDetails().observe(viewLifecycleOwner, Observer { it ->
                if (it != null) {
                    Glide.with(originalImage.context)
                        .load(it.photo)
                        .placeholder(R.drawable.ic_image_placeholder_64dp)
                        .listener(object : RequestListener<Drawable> {
                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: Target<Drawable>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                progress.hide()
                                return false
                            }

                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                progress.hide()
                                return false
                            }
                        })
                        .into(originalImage)
                    descriptionText.setText(it.description)
                    venueNameText.setText(it.name)
                    likesText.setText(it.likes)
                    rateText.setText(it.rating.toString())
                }
            })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        with(toolbar) {
            setNavigationOnClickListener { navigator.onBack() }
        }
    }

}
