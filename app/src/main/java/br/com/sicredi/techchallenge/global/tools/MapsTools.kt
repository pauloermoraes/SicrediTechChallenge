package br.com.sicredi.techchallenge.global.tools

import android.app.Activity
import android.content.Intent
import android.net.Uri
import br.com.sicredi.techchallenge.R

object MapsTools {

    //<editor-fold desc="Open Google Maps">
    fun openGoogleMaps(activity: Activity, latitude: Float, longitude: Float) {
        openGoogleMaps(activity, Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude"))
    }

    fun openGoogleMaps(activity: Activity, uri: Uri?) {
        try {
            val mapIntent = Intent(Intent.ACTION_VIEW, uri)
            mapIntent.setPackage("com.google.android.apps.maps")
            activity.startActivity(mapIntent)
        } catch (e: Exception) {
            AlertDialogTools.alertDialog(
                activity,
                "",
                activity.getString(R.string.googlemaps_not_found)
            )
        }
    }
    //</editor-fold>
}