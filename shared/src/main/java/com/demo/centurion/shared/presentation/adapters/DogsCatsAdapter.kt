
package com.demo.centurion.shared.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.demo.centurion.shared.R
import com.demo.centurion.shared.databinding.ItemCatsDogsBinding
import com.demo.centurion.shared.presentation.states.UIModel

class DogsCatsAdapter : ListAdapter<UIModel, DogsCatsAdapter.DogsCatViewHolder>(DogsCatDiffUtil) {

  class DogsCatViewHolder(private val itemCatsDogsBinding: ItemCatsDogsBinding) : RecyclerView.ViewHolder(itemCatsDogsBinding.root) {

    fun bind(uiModel: UIModel) {
      when {
        uiModel.url.contains("https") -> {
          itemCatsDogsBinding.image.load(uiModel.url)
        }
        else -> {
          Log.d("Cat", "https://cataas.com/cat/${uiModel.url}")
          itemCatsDogsBinding.image.setImageResource(R.drawable.issue_drawable_image)
        }
      }
    }
  }

  object DogsCatDiffUtil : DiffUtil.ItemCallback<UIModel>() {
    override fun areItemsTheSame(oldItem: UIModel, newItem: UIModel): Boolean {
      return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: UIModel, newItem: UIModel): Boolean {
      return oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsCatViewHolder =
      DogsCatViewHolder(
          ItemCatsDogsBinding.inflate(
              LayoutInflater.from(parent.context),
              parent,
              false
          )
      )

  override fun onBindViewHolder(holder: DogsCatViewHolder, position: Int) {
    holder.bind(getItem(position))
  }
}