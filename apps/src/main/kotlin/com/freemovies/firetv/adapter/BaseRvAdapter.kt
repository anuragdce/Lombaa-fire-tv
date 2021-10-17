package com.freemovies.firetv.adapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.freemovies.firetv.utils.RequiresVhLayoutResId

abstract class BaseRvAdapter<ItemType, BindingType : ViewDataBinding>(
    protected val comparator: IDiffComparator<ItemType>
) : RecyclerView.Adapter<BaseVh<BindingType>>(), IAdapter<ItemType>,
    RequiresVhLayoutResId {
    private val dataSet = ArrayList<ItemType>()
    protected fun getItem(position: Int) = dataSet[position]
    override fun getItemCount() = dataSet.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseVh.inflate<BindingType>(parent, getLayoutRes(viewType))

    override fun submitList(list: List<ItemType>?) {
        val newDataSet = list.orEmpty()

        val diffResult = DiffUtil.calculateDiff(getDiffCallback(dataSet, newDataSet, comparator))
        dataSet.clear()
        dataSet.addAll(newDataSet)
        diffResult.dispatchUpdatesTo(this)
    }

    private fun getDiffCallback(
        oldData: List<ItemType>,
        newData: List<ItemType>,
        comparator: IDiffComparator<ItemType>
    ): DiffUtil.Callback {
        return object : DiffUtil.Callback() {

            override fun getOldListSize() = oldData.size

            override fun getNewListSize() = newData.size

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                comparator.areItemsTheSame(oldData[oldItemPosition], newData[newItemPosition])

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
                comparator.areContentsTheSame(oldData[oldItemPosition], newData[newItemPosition])
        }
    }
}
