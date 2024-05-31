package com.eskaya.task_sanstech.domain.use_case

import com.eskaya.task_sanstech.data.remote.models.response.MatchListDto
import com.eskaya.task_sanstech.domain.repository.MatchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import com.eskaya.task_sanstech.utils.Resource
import com.eskaya.task_sanstech.utils.extensions.handleError

class GetMatchListUseCase @Inject constructor(
    private val repository: MatchRepository
) {
    operator fun invoke(): Flow<Resource<MatchListDto>> = flow {
        try {
            emit(Resource.Loading())
            val matches = repository.getMatchList()
            emit(Resource.Success(data = matches))
        } catch (e: HttpException) {
            emit(Resource.Error(e.handleError()))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }


}