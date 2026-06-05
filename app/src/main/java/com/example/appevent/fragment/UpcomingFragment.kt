package com.example.appevent.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appevent.R
import com.example.appevent.ui.adapter.EventAdapter
import com.example.appevent.ui.viewmodel.EventViewModel
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerFrameLayout

class UpcomingFragment : Fragment() {
    private val viewModel: EventViewModel by viewModels()
    private lateinit var eventAdapter: EventAdapter
    private lateinit var shimmer: ShimmerFrameLayout
    private lateinit var swipeRefresh: androidx.swiperefreshlayout.widget.SwipeRefreshLayout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upcoming, container, false)
        shimmer = view.findViewById(R.id.shimmerLayout)
        swipeRefresh = view.findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            viewModel.fetchActiveEvents()
        }

        // 1. Pasang adapter kosong satu kali saja di awal
        eventAdapter = EventAdapter(emptyList())
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = eventAdapter

        viewModel.fetchActiveEvents()

        // 2. Saat data datang, cukup "suntikkan" data barunya ke adapter yang sudah ada
        viewModel.eventsActive.observe(viewLifecycleOwner) { events ->
            eventAdapter.updateData(events)
        }

        viewModel.isLoadingUpcoming.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                shimmer.startShimmer()
                shimmer.visibility = View.VISIBLE
            } else {
                shimmer.stopShimmer()
                shimmer.visibility = View.GONE
                swipeRefresh.isRefreshing = false
            }
        }
        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
            }
        }
        return view
    }
}
