package ui.screens.list

import model.PlaceMark

/**
 * Created by hossam
 */
interface CarsListContract {
    interface Presenter {
        fun attach(view: CarsListContract.View)
        fun detach()
        fun mapClicked()
        fun start()
    }

    interface View {
        fun init()
        fun populateList(placeMarks: List<PlaceMark>)
    }
}