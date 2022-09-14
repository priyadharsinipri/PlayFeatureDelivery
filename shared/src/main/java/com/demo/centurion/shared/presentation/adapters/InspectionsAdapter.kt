package com.demo.centurion.shared.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.centurion.shared.databinding.ItemCatsDogsBinding
import com.demo.centurion.shared.presentation.states.UIModel
import kotlin.random.Random

class InspectionsAdapter :
    ListAdapter<UIModel, InspectionsAdapter.InspectionsViewHolder>(DogsCatDiffUtil) {

    class InspectionsViewHolder(private val itemCatsDogsBinding: ItemCatsDogsBinding) :
        RecyclerView.ViewHolder(itemCatsDogsBinding.root) {

        fun bind(uiModel: UIModel) {
            itemCatsDogsBinding.number.text = uiModel.checklistNumber
            itemCatsDogsBinding.description.text = uiModel.checklistTitle + Random(6).toString()
            // itemCatsDogsBinding.image.setImageResource(R.drawable.issue_drawable_image)
        }
    }


    object DogsCatDiffUtil : DiffUtil.ItemCallback<UIModel>() {
        override fun areItemsTheSame(oldItem: UIModel, newItem: UIModel): Boolean {
            return (oldItem.checklistNumber == newItem.checklistNumber && oldItem.checklistTitle == newItem.checklistTitle)
        }

        override fun areContentsTheSame(oldItem: UIModel, newItem: UIModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InspectionsViewHolder =
        InspectionsViewHolder(
            ItemCatsDogsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: InspectionsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}