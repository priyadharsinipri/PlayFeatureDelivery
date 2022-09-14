package com.demo.centurion.playfeaturedelivery

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.demo.centurion.playfeaturedelivery.databinding.ActivityMainBinding
import com.google.android.play.core.splitinstall.SplitInstallManager
import com.google.android.play.core.splitinstall.SplitInstallManagerFactory
import com.google.android.play.core.splitinstall.SplitInstallRequest
import com.google.android.play.core.splitinstall.SplitInstallStateUpdatedListener
import com.google.android.play.core.splitinstall.model.SplitInstallSessionStatus
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * Main Screen
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val splitInstallManager: SplitInstallManager by lazy {
        SplitInstallManagerFactory.create(
            this
        )
    }
   private val listener = SplitInstallStateUpdatedListener { state ->
        when (state.status()) {
            SplitInstallSessionStatus.DOWNLOADING -> {
                binding.progressIndicator.visibility = View.VISIBLE
                Toast.makeText(applicationContext, "Downloading", Toast.LENGTH_SHORT).show()
            }
            SplitInstallSessionStatus.INSTALLING -> {
                binding.progressIndicator.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    "Module Installing",
                    Toast.LENGTH_SHORT
                ).show()
            }
            SplitInstallSessionStatus.INSTALLED -> {
                binding.progressIndicator.visibility = View.GONE
                Toast.makeText(
                    applicationContext,
                    "Module Download Completed",
                    Toast.LENGTH_SHORT
                ).show()
                val intent = Intent()
                intent.setClassName(
                    BuildConfig.APPLICATION_ID,
                    "com.demo.centurion.cats.CatsActivity"
                )
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupModulesDownload()

        binding.dogsCard.setOnClickListener {
            val intent = Intent()
            intent.setClassName(BuildConfig.APPLICATION_ID, "com.demo.centurion.dogs.DogsActivity")
            startActivity(intent)
        }

    }

    override fun onPause() {
        splitInstallManager.unregisterListener(listener)
        super.onPause()
    }

    override fun onResume() {
        splitInstallManager.registerListener(listener)
        super.onResume()
    }

    private fun setupModulesDownload() {

        val catsModuleInstallRequest = SplitInstallRequest.newBuilder()
            .addModule(CATS_MODULE)
            .build()
        binding.catsCard.setOnClickListener {
            splitInstallManager.startInstall(catsModuleInstallRequest)
        }

        binding.catsCard.setOnLongClickListener() {
            showDialog()
            true
        }

    }

    private fun showDialog() {
        var builder = AlertDialog.Builder(this)
        builder.setTitle("Uninstall the module").setCancelable(false).setPositiveButton(
            "Uninstall"
        ) { dialog, id ->
            deleteModule()
            dialog.dismiss()
        }.show()

    }

    fun deleteModule() {
        uninstallModule()
    }

    private fun uninstallModule() {
        CoroutineScope(Dispatchers.IO).launch {
            splitInstallManager.deferredUninstall(listOf(CATS_MODULE)).addOnSuccessListener{
                Toast.makeText(
                    applicationContext,
                    "Uninstallation Completed successfully",
                    Toast.LENGTH_SHORT
                ).show()
            }.addOnFailureListener {
                Toast.makeText(
                    applicationContext,
                    "Uninstallation Failed ",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object {
        private const val DOGS_MODULE = "dog"
        private const val CATS_MODULE = "cats"
    }
}



