package ritsam.intercom.invite.me.util

import org.junit.Assert.*
import org.junit.Test
import ritsam.intercom.invite.me.data.model.LatLong

class GPSGreatCircleUtilTest {

    companion object {
        const val SEARCH_RADIUS = 100
        const val RADIUS_EARTH = 6371
        const val RADIUS_MARS = 3389
        const val INTERCOM_CUSTOMER_LATITUDE = "53.2451022"
        const val INTERCOM_CUSTOMER_LONGITUDE = "-6.238335"
    }

       @Test
       fun `successful distance calculation on Earth`(){
           val distance = GPSGreatCircleUtil.calcDistance(
               planetRadius = RADIUS_EARTH,
               customerLocation = LatLong(latitude = INTERCOM_CUSTOMER_LATITUDE.toDouble(),
               longitude =  INTERCOM_CUSTOMER_LONGITUDE.toDouble())
           )
           assertEquals(distance,10.488,0.1)
       }

    @Test
    fun `failed distance calculation on Earth`(){
        val distance = GPSGreatCircleUtil.calcDistance(
            planetRadius = RADIUS_EARTH,
            customerLocation = LatLong(latitude = INTERCOM_CUSTOMER_LATITUDE.toDouble(),
                longitude =  INTERCOM_CUSTOMER_LONGITUDE.toDouble())
        )
        assertNotEquals(distance,115.488,0.1)
    }

    @Test
       fun `successful distance calculation on Mars`(){
           val distance = GPSGreatCircleUtil.calcDistance(
               planetRadius = RADIUS_MARS,
               customerLocation = LatLong(latitude = INTERCOM_CUSTOMER_LATITUDE.toDouble(),
               longitude =  INTERCOM_CUSTOMER_LONGITUDE.toDouble())
           )
           assertEquals(distance,5.579,0.1)
       }
}