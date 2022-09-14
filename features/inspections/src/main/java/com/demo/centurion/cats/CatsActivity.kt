
package com.demo.centurion.cats

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.google.android.play.core.splitcompat.SplitCompat
import com.demo.centurion.shared.R
import com.demo.centurion.shared.databinding.ActivityCatsDogsBinding
import com.demo.centurion.shared.presentation.adapters.DogsCatsAdapter
import com.demo.centurion.shared.presentation.states.UIModel
import com.demo.centurion.shared.presentation.states.UIState
import com.demo.centurion.shared.presentation.viewmodels.CatsDogViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CatsActivity : AppCompatActivity() {
  private val catsDogViewModel: CatsDogViewModel by viewModel()
  private val catsDogsAdapter = DogsCatsAdapter()
  private lateinit var binding: ActivityCatsDogsBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityCatsDogsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    catsDogViewModel.getCats()
    binding.rv.adapter = catsDogsAdapter
    observeCats()

  }

  private fun observeCats() {
    lifecycleScope.launch {
      catsDogViewModel.cats.flowWithLifecycle(lifecycle).collect { value: UIState ->
        when (value) {
          is UIState.ShowData<*> -> {
            binding.animationView.cancelAnimation()
            binding.animationView.visibility = View.GONE
            populateData(value.data as List<UIModel>)
          }
          is UIState.Error -> {
            binding.animationView.cancelAnimation()
            binding.animationView.visibility = View.GONE
            Toast.makeText(applicationContext, value.message, Toast.LENGTH_SHORT).show()
          }
          UIState.Loading -> {
            binding.animationView.apply {
              setAnimation(R.raw.cat_animation)
              playAnimation()
              visibility = View.VISIBLE
            }
          }
        }
      }
    }
  }

  private fun populateData(data: List<UIModel>) {
    catsDogsAdapter.submitList(data)
  }

  override fun attachBaseContext(base: Context?) {
    super.attachBaseContext(base)
    SplitCompat.install(this)
  }
}