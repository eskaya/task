package com.eskaya.task_sanstech.presentation.match_list

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eskaya.mvvm_application.databinding.FragmentMatchListBinding
import com.eskaya.task_sanstech.data.AppPreferences
import com.eskaya.task_sanstech.data.remote.models.League
import com.eskaya.task_sanstech.domain.model.Match
import com.eskaya.task_sanstech.presentation.match_detail.MatchDetailActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MatchListFragment : Fragment(), MatchAdapterListener {

    private lateinit var binding: FragmentMatchListBinding
    private val viewModel: MatchViewModel by viewModels()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var ligListAdapter: LigListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMatchListBinding.inflate(inflater, container, false)
        viewModel.getMatchList()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        listener()
        setUpObservers()
    }

    private fun init() {
        layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerViewForLigList.layoutManager = layoutManager
    }

    private fun listener() {
    }

    private fun setUpObservers() {
        viewModel.getViewState.observe(
            viewLifecycleOwner
        ) { it ->
            when (it) {
                MatchListViewState.Init -> Unit
                is MatchListViewState.Error -> handleError(it.error)
                is MatchListViewState.IsLoading -> handleLoading(it.isLoading)
                is MatchListViewState.Success -> handleSuccess(
                    it.data
                )
            }
        }
    }

    private fun handleSuccess(data: List<League>) {
        ligListAdapter = LigListAdapter(data, this)!!
        binding.recyclerViewForLigList.adapter = ligListAdapter
        binding.recyclerViewForLigList.adapter = ligListAdapter
    }

    private fun handleLoading(loading: Boolean) {
    }

    private fun handleError(error: Any) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onClickedItem(match: Match) {
        val intent = Intent(activity, MatchDetailActivity::class.java)
        intent.putExtra("match", match)
        startActivity(intent)
    }

    override fun onStarClick(matchId: Int) {
        if (AppPreferences.getInstance(requireContext()).isFavorite(matchId)) {
            AppPreferences.getInstance(requireContext()).removeFavorite(matchId)
        } else {
            AppPreferences.getInstance(requireContext()).addFavorite(matchId)
        }
        ligListAdapter.notifyDataSetChanged()
    }

}