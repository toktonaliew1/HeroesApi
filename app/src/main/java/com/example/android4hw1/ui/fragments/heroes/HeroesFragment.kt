package com.example.android4hw1.ui.fragments.heroes

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.android4hw1.Either
import com.example.android4hw1.R
import com.example.android4hw1.base.BaseFragment
import com.example.android4hw1.databinding.FragmentHeroesBinding
import com.example.android4hw1.ui.adapters.HeroesAdapter

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeroesFragment :  BaseFragment<HeroesViewModel, FragmentHeroesBinding>(R.layout.fragment_heroes) {


    override val binding by viewBinding(FragmentHeroesBinding::bind)
    override val viewModel: HeroesViewModel by viewModels()
    private val heroesAdapter = HeroesAdapter(
        this::onItemClick
    )

    override fun initialize() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() = with(binding.recyclerHeroes) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = heroesAdapter
    }

    override fun setupSubscribes() {
        setupObserv()
    }

    private fun setupObserv() {
        viewModel.fetchHeroes().observe(viewLifecycleOwner) {
            when (it) {
                is Either.Left -> {
                    Log.e("anime", it.value)
                }
                is Either.Right -> {
                    Log.e("Anime", it.value.toString())
                    heroesAdapter.submitList(it.value)
                }
            }
        }
    }


    private fun onItemClick(id: Int) {
        findNavController().navigate(
            HeroesFragmentDirections.actionHeroesFragmentToHeroesDetailFragment2(
                position = id
            )
        )
    }
}



