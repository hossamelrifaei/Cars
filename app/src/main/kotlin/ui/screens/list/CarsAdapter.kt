package ui.screens.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cars.R
import kotlinx.android.synthetic.main.list_item.view.*
import model.PlaceMark

/**
 * Created by hossam
 */
class CarsAdapter : RecyclerView.Adapter<CarsAdapter.CarsViewHolder>() {
    private var carsList: MutableList<PlaceMark> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarsViewHolder {
        return CarsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return carsList.size
    }

    override fun onBindViewHolder(holder: CarsViewHolder, position: Int) {
        holder.bind(carsList[position])
    }

    fun addCars(carsList: MutableList<PlaceMark>) {
        this.carsList = carsList
        notifyDataSetChanged()
    }

    inner class CarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(placeMark: PlaceMark) {
            itemView.apply {
                name.text = placeMark.name
                vin.text = placeMark.vin
            }
        }
    }

}