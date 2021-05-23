package ritsam.intercom.invite.me.util

import ritsam.intercom.invite.me.data.model.LatLong
import kotlin.math.*


/*
* Utility to accept customer location data and calculate
* the distance between the office and the customer
* */
object GPSUtil {

    fun calcDistance(planetRadius: Int, customerLocation: LatLong): Double {
        var officeLatLocation = LatLong(
            latitude = Constants.INTERCOM_OFFICE_LATITUDE.toDouble(),
            longitude = Constants.INTERCOM_OFFICE_LONGITUDE.toDouble()
        )
        var latDistance = Math.toRadians(customerLocation.latitude - officeLatLocation.latitude)
        var lonDistance =
            Math.toRadians(customerLocation.latitude - officeLatLocation.longitude)
        var a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
        +cos(Math.toRadians(officeLatLocation.latitude)) * cos(
            Math.toRadians(
                customerLocation.latitude
            )
        ) * sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        var  c = 2 * atan2(sqrt(a), sqrt(1-a));

        return sqrt((planetRadius * c).pow(2.0))
    }


}