package com.demo.centurion.shared.presentation.adapters



import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.centurion.shared.R
import com.demo.centurion.shared.databinding.ItemCatsDogsBinding
import com.demo.centurion.shared.presentation.states.UIIssueModel

class IssuesAdapter : ListAdapter<UIIssueModel, IssuesAdapter.IssuesViewHolder>(DogsCatDiffUtil) {

    class IssuesViewHolder(private val itemCatsDogsBinding: ItemCatsDogsBinding) : RecyclerView.ViewHolder(itemCatsDogsBinding.root) {

        fun bind(uiModel: UIIssueModel) {
            itemCatsDogsBinding.number.text = uiModel.issueNumber
            itemCatsDogsBinding.description.text = uiModel.description
           // itemCatsDogsBinding.image.setImageResource(R.drawable.issue_drawable_image)
        }
    }

    object DogsCatDiffUtil : DiffUtil.ItemCallback<UIIssueModel>() {
        override fun areItemsTheSame(oldItem: UIIssueModel, newItem: UIIssueModel): Boolean {
            return oldItem.issueNumber == newItem.issueNumber
        }

        override fun areContentsTheSame(oldItem: UIIssueModel, newItem: UIIssueModel): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssuesViewHolder =
        IssuesViewHolder(
            ItemCatsDogsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: IssuesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}