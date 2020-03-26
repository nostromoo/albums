package com.romain.pedepoy.albums

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.observe
import com.google.android.material.snackbar.Snackbar
import com.romain.pedepoy.albums.adapter.AlbumAdapter
import com.romain.pedepoy.albums.databinding.ActivityMainBinding
import com.romain.pedepoy.albums.viewmodels.AlbumListViewModel
import com.romain.pedepoy.albums.data.Result
import dagger.android.AndroidInjection
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel : AlbumListViewModel
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        binding = setContentView(this, R.layout.activity_main)

        val adapter = AlbumAdapter()
        binding.albumList.adapter = adapter
        binding.albumList.setHasFixedSize(true)

        subscribeToModel()
    }

    private fun subscribeToModel() {
        viewModel.albums.observe(this) { result ->
            when (result.status) {
                Result.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    result.data?.let { (binding.albumList.adapter as AlbumAdapter).submitList(it) }
                }
                Result.Status.LOADING -> { binding.progressBar.visibility = View.VISIBLE }
                Result.Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    result.message?.let { Snackbar.make(binding.constraintLayout, it, Snackbar.LENGTH_LONG).show() }
                }
            }
        }
    }
}
