package com.eskaya.task_sanstech.presentation.match_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.eskaya.mvvm_application.databinding.FragmentMatchListBinding
import com.eskaya.task_sanstech.data.remote.models.response.Data
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MatchListFragment : Fragment() {

    private lateinit var binding: FragmentMatchListBinding
    private val viewModel: MatchViewModel by viewModels()

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
    }

    private fun listener() {}

    private fun setUpObservers() {
        viewModel.getViewState.observe(
            viewLifecycleOwner
        ) { it ->
            when (it) {
                MatchListViewState.Init -> Unit
                is MatchListViewState.Error -> handleError(it.error)
                is MatchListViewState.IsLoading -> handleLoading(it.isLoading)
                is MatchListViewState.Success -> it.data?.let {
                    handleSuccess(
                        it
                    )
                }
            }
        }
    }

    private fun handleSuccess(data: Map<Pair<String, String>, List<Data>>) {

        for ((ligBilgisi, macListesi) in data) {
            val (ligAdi, bayrak) = ligBilgisi
            println("Lig Adı: $ligAdi")
            println("Bayrak: $bayrak")
            println("Maçlar:")
            for (mac in macListesi) {
                println("- ${mac.ht.n} vs ${mac.at.n}")
            }
            println("----------")
        }

    }

    private fun handleLoading(loading: Boolean) {
    }

    private fun handleError(error: Any) {
        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
    }

}