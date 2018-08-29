package ui.screens.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import base.BaseFragment
import com.cars.R
import kotlinx.android.synthetic.main.fragment_list.*
import model.PlaceMark
import javax.inject.Inject

/**
 * Created by hossam
 */
class ListFragment : BaseFragment(), CarsListContract.View {
    companion object {
        @JvmStatic
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

    @Inject
    lateinit var presenter: CarsListPresenterImpl

    override fun populateList(placeMarks: List<PlaceMark>) {
        (list.adapter as CarsAdapter).addCars(placeMarks.toMutableList())
    }

    override fun init() {
        list.adapter = CarsAdapter()
        list.layoutManager = LinearLayoutManager(context)
        fab.setOnClickListener { presenter.mapClicked() }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun detach() {
        presenter.detach()
    }

    override fun layoutRes(): Int {
        return R.layout.fragment_list
    }
}