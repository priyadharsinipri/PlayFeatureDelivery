
package com.demo.centurion.issues

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.demo.centurion.shared.R
import com.demo.centurion.shared.databinding.ActivityCatsDogsBinding
import com.demo.centurion.shared.presentation.adapters.IssuesAdapter
import com.demo.centurion.shared.presentation.states.UIIssueModel
import com.demo.centurion.shared.presentation.states.UIState
import com.demo.centurion.shared.presentation.viewmodels.CatsDogViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DogsActivity : AppCompatActivity() {
  private val catsDogViewModel: CatsDogViewModel by viewModel()
  private val catsDogsAdapter = IssuesAdapter()
  private lateinit var binding: ActivityCatsDogsBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityCatsDogsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    catsDogViewModel.getDogs()
    binding.rv.adapter = catsDogsAdapter
    observeDogs()

  }

  private fun observeDogs() {
    lifecycleScope.launch {
      catsDogViewModel.dogs.flowWithLifecycle(lifecycle).collect { value: UIState ->
        when (value) {
          is UIState.ShowData<*> -> {
            binding.animationView.cancelAnimation()
            binding.animationView.visibility = View.GONE
            populateData(value.data as List<UIIssueModel>)
          }
          is UIState.Error -> {
            Toast.makeText(applicationContext, value.message, Toast.LENGTH_SHORT).show()
            binding.animationView.cancelAnimation()
            binding.animationView.visibility = View.GONE
          }
          UIState.Loading -> {
            binding.animationView.apply {
              setAnimation(R.raw.dog_animation)
              playAnimation()
              visibility = View.VISIBLE
            }

          }
        }
      }
    }
  }

  private fun populateData(data: List<UIIssueModel>) {
    catsDogsAdapter.submitList(data)
  }
}