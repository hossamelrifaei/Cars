package home

/**
 * Created by hossam
 */
interface HomeContract {
    interface Presenter {
        fun load()
        fun grantPermission()
        fun attach(view: HomeContract.View)
        fun detach()
        fun permissionResult(requestCode: Int, grantResults: IntArray)
    }

    interface View {
        fun requestPermission()
    }
}