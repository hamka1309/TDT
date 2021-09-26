package com.kma.learnnavigationcomponent.language

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.kma.learnnavigationcomponent.R

class CheckboxAdapter : RecyclerView.Adapter<CheckboxAdapter.ViewHolder>() {
    var listCheckboxModel: ArrayList<CheckBoxModel>? = ArrayList()
    var adapterOnClick: AdapterOnClick? = null

    interface AdapterOnClick {
        fun onClickItem(item: Int)
    }

    fun setListCheckbox(list: ArrayList<CheckBoxModel>) {
        this.listCheckboxModel = list
        notifyDataSetChanged()
    }

    fun setCallback(list: AdapterOnClick) {
        adapterOnClick = list
    }

    open class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: CheckBox = itemView.findViewById(R.id.cbSelectAll)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_recycleview, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        if (position == 0) {
//            holder.itemView.visibility = View.GONE
//        }
        val itemData = listCheckboxModel?.get(position)
        holder.imageView.text = listCheckboxModel?.get(position)?.text
        holder.imageView.setOnClickListener {
            itemData?.isCheck = holder.imageView.isChecked
            adapterOnClick?.onClickItem(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)

    }

    override fun getItemCount(): Int = listCheckboxModel!!.size

}