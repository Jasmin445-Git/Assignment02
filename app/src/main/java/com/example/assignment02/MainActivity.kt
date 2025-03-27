package com.example.assignment02

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.assignment02.data.model.Movie
import com.example.assignment02.databinding.ActivityMainBinding
import com.example.assignment02.ui.MovieAdapter
import com.example.assignment02.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MovieViewModel>()
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize RecyclerView Adapter
        setupRecyclerView()

        // Handle search button click
        binding.btnSearch.setOnClickListener {
            val query = binding.etSearch.text.toString().trim()

            if (query.isNotEmpty()) {
                viewModel.searchMovies(query, "17cff8e7") // Replace with your API key
            } else {
                showToast("Please enter a movie title")
            }
        }


        viewModel.movies.observe(this) { movies ->
            adapter.updateMovies(movies)
        }
    }


    private fun setupRecyclerView() {
        adapter = MovieAdapter(emptyList()) { movie ->
            navigateToDetails(movie)
        }
        binding.rvMovies.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
    }


    private fun navigateToDetails(movie: Movie) {
        val intent = Intent(this, DetailsActivity::class.java).apply {
            putExtra("movie_id", movie.imdbID)
        }
        startActivity(intent)
    }


    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
