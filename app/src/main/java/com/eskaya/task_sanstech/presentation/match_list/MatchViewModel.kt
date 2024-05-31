package com.eskaya.task_sanstech.presentation.match_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eskaya.task_sanstech.data.remote.models.response.MatchListDto
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
                    _state.value = MatchListViewState.Success(it.data)
                }
            }

        }.launchIn(viewModelScope)
    }
}

sealed class MatchListViewState {
    object Init : MatchListViewState()
    data class Success(val data: MatchListDto?) : MatchListViewState()
    data class IsLoading(val isLoading: Boolean) : MatchListViewState()
    data class Error(val error: Any) : MatchListViewState()
}



