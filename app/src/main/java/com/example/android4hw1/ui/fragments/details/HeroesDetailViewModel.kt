package com.example.android4hw1.ui.fragments.details

import androidx.lifecycle.ViewModel
import com.example.android4hw1.data.repository.HeroesRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HeroesDetailViewModel @Inject constructor(private val repository: HeroesRepository) :
    ViewModel() {

    fun fetchHeroesId(position: Int) = repository.fetchHeroesId(position)
}