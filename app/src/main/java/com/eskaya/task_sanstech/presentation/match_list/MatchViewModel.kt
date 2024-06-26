package com.eskaya.task_sanstech.presentation.match_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eskaya.task_sanstech.data.remote.models.League
import com.eskaya.task_sanstech.domain.model.Match
import com.eskaya.task_sanstech.domain.use_case.GetMatchListUseCase
import com.eskaya.task_sanstech.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MatchViewModel @Inject constructor(
    private val getMatchListUseCase: GetMatchListUseCase
) : ViewModel() {


    private val _state = MutableLiveData<MatchListViewState>(MatchListViewState.Init)
    val getViewState: LiveData<MatchListViewState> get() = _state


    private fun setLoadingState(isLoading: Boolean) {
        _state.value = MatchListViewState.IsLoading(isLoading)
    }

    fun getMatchList() {
        getMatchListUseCase.invoke().onEach {
            when (it) {
                is Resource.Error -> {
                    setLoadingState(false)
                    _state.value = MatchListViewState.Error(it.message as Any)
                }

                is Resource.Loading -> {
                    setLoadingState(true)
                }

                is Resource.Success -> {
                    setLoadingState(false)
                    it.data?.let { it1 -> filterData(it1) }
                }
            }

        }.launchIn(viewModelScope)
    }


    private fun filterData(data: List<Match>) {

        //Filtering only finished matches
        val filteredMatches = data.filter { it.scorInfos?.st  == 5 }
        //It is expected to be sorted by date
        val sortedMatches = filteredMatches.sortedBy { it.matchDate }
        //Grouping by leagues
        val groupedMatches = sortedMatches.groupBy { (it.to?.n ) to it.to?.flag }

        val leagues = groupedMatches.map { entry ->
            entry.key.first?.let {
                entry.key.second?.let { it1 ->
                    League(
                        leagueName = it,
                        flag = it1,
                        matches = entry.value
                    )
                }
            }
        }
        _state.value = leagues.let { MatchListViewState.Success(it as List<League>) }
    }
}

sealed class MatchListViewState {
    object Init : MatchListViewState()
    data class Success(val data: List<League>) : MatchListViewState()
    data class IsLoading(val isLoading: Boolean) : MatchListViewState()
    data class Error(val error: Any) : MatchListViewState()
}



