package model

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

/**
 * Created by hossam
 */
class MapMarkerItem(private val position: LatLng, private val title: String, private val snippet: String? = null) : ClusterItem {
    override fun getSnippet(): String? {
        return snippet
    }

    override fun getTitle(): String {
        return title
    }

    override fun getPosition(): LatLng {
        return position
    }
}