package me.meikiem.foursquarevenuesnearby.data.repository

import me.meikiem.foursquarevenuesnearby.data.util.API_DATE_FORMAT
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/21/20.
 */

fun getTodayDateFormatted(): String {
    val formatter = SimpleDateFormat(API_DATE_FORMAT, Locale.getDefault())
    val date = Calendar.getInstance().time
    return formatter.format(date)
}