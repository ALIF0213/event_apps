package com.example.appevent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appevent.R
import com.example.appevent.ui.adapter.EventAdapter
import com.example.appevent.ui.viewmodel.FavoriteViewModel
import com.example.appevent.ui.viewmodel.FavoriteViewModelFactory

class FavoriteFragment : Fragment() {
    private val viewModel: FavoriteViewModel by viewModels {
        FavoriteViewModelFactory(requireActivity().application)
    }
    private lateinit var eventAdapter: EventAdapter
    private lateinit var tvEmptyFavorite: TextView // 1. Deklarasi TextView kosong

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorite, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        tvEmptyFavorite = view.findViewById(R.id.tvEmptyFavorite) // 2. Inisialisasi ID

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        eventAdapter = EventAdapter(emptyList())
        recyclerView.adapter = eventAdapter

        // 3. Logika dimasukkan ke dalam area yang memantau perubahan data
        viewModel.favoriteEvents.observe(viewLifecycleOwner) { favorites ->
            eventAdapter.updateData(favorites)

            // Cek apakah data favorit yang datang itu kosong?
            if (favorites.isEmpty()) {
                tvEmptyFavorite.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            } else {
                tvEmptyFavorite.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
        }

        return view
    }
}