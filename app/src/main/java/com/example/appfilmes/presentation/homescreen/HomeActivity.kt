package com.example.appfilmes.presentation.homescreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.appfilmes.MainActivity
import com.example.appfilmes.R
import com.example.appfilmes.data.local.AppDatabase
import com.example.appfilmes.data.repository.MovieRepository
import com.example.appfilmes.databinding.ItemMovieBinding

import com.example.appfilmes.databinding.MainActivityBinding
import com.example.appfilmes.presentation.homescreen.HomeViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private val repository = MovieRepository(AppDatabase.getInstance(this))
    private var viewModel = HomeViewModel(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movies.observe(this) { movies ->
            binding.moviesContainer.removeAllViews()  // limpa antes de adicionar

            for (movieWithGenres in movies) {
                val movie = movieWithGenres.movie

                // Infla o card usando o Binding do item
                val itemBinding = ItemMovieBinding.inflate(
                    layoutInflater,
                    binding.moviesContainer,
                    false
                )

                itemBinding.txtTitulo.text = movie.title

                val imageResId = resources.getIdentifier(
                    movie.posterPath?.removeSuffix(".jpg"),
                    "drawable",
                    packageName
                )
                if (imageResId != 0) {
                    itemBinding.imgPoster.setImageResource(imageResId)
                }

                // Adiciona a View raiz do binding no container principal
                binding.moviesContainer.addView(itemBinding.root as android.view.View)
            }
        }
    }
}