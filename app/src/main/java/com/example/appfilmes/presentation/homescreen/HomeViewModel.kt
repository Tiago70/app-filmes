package com.example.appfilmes.presentation.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.appfilmes.data.local.MovieWithGenres

import com.example.appfilmes.data.repository.MovieRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: MovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieWithGenres>>()
    val movies: LiveData<List<MovieWithGenres>> = _movies

    fun loadMovies() {
        viewModelScope.launch {
            _movies.value = repository.getPopularMovies()
        }
    }
}