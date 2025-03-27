package com.example.assignment02

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.assignment02.databinding.ActivityDetailsBinding
import com.example.assignment02.viewmodel.MovieViewModel

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val viewModel by viewModels<MovieViewModel>()
    private val apiKey = "362a0057"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val imdbId = intent.extras?.getString("movie_id") ?: ""
        if (imdbId.isNotEmpty()) {
            viewModel.fetchMovieDetails(imdbId, apiKey)
        }


        viewModel.movieDetails.observe(this) { movie ->
            movie?.let {
                binding.apply {
                    tvDetailsTitle.text = it.Title
                    tvDetailsYear.text = "${it.Year} • ${it.Runtime} • ${it.Genre}\n\nIMDb Rating: ${it.imdbRating}\n\n${it.Plot}"


                    Glide.with(this@DetailsActivity)
                        .load(it.Poster)
                        .into(imgDetailsPoster)
                }
            }
        }


        binding.btnGoBack.setOnClickListener { finish() }
    }
}
