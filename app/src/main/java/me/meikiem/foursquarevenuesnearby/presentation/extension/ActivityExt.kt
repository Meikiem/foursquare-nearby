package me.meikiem.foursquarevenuesnearby.presentation.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * Created by mohammad.hossein.safari.langaroudi on 4/18/20.
 */

fun AppCompatActivity.checkSelfPermissionCompat(permission: String) =
    ActivityCompat.checkSelfPermission(this, permission)

fun AppCompatActivity.shouldShowRequestPermissionRationaleCompat(permission: String) =
    ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun AppCompatActivity.requestPermissionsCompat(permissionsArray: Array<String>, requestCode: Int) {
    ActivityCompat.requestPermissions(this, permissionsArray, requestCode)
}