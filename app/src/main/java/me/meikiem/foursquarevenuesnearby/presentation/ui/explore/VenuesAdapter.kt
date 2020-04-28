package me.meikiem.foursquarevenuesnearby.presentation.ui.explore

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_explore.*
import me.meikiem.foursquarevenuesnearby.R
import me.meikiem.foursquarevenuesnearby.data.repository.NetworkState
import me.meikiem.foursquarevenuesnearby.domain.entity.VenueObject
import me.meikiem.foursquarevenuesnearby.presentation.extension.inflate

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

class VenuesAdapter(
    private val clickListener: (VenueObject) -> Unit
)
    : PagedListAdapter<VenueObject, VenueViewHolder>(VENUE_COMPARATOR){
    private var networkState: NetworkState? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VenueViewHolder(parent.inflate(R.layout.item_explore), clickListener)


    override fun onBindViewHolder(holder: VenueViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        val VENUE_COMPARATOR = object : DiffUtil.ItemCallback<VenueObject>() {
            override fun areContentsTheSame(oldItem: VenueObject, newItem: VenueObject): Boolean =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: VenueObject, newItem: VenueObject): Boolean =
                oldItem.name == newItem.name
        }
    }
}


class VenueViewHolder(
    override val containerView: View,
    private val clickListener: (VenueObject) -> Unit
) :
    RecyclerView.ViewHolder(containerView), LayoutContainer {
    fun bind(item: VenueObject) {
        placeNameText.text = item.name
        captionText.text = "location latLng: ${roundFormat(item.latitude)}, ${roundFormat(item.longitude)}"
        cardView.setOnClickListener { clickListener(item) }
    }


}
 fun roundFormat(ll:Double):Double{
    val number3digits:Double = Math.round(ll * 1000.0) / 1000.0
    val number2digits:Double = Math.round(number3digits * 100.0) / 100.0
    val solution:Double = Math.round(number2digits * 10.0) / 10.0
    return solution
}