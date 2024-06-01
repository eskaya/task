import com.eskaya.task_sanstech.domain.model.Match
import com.eskaya.task_sanstech.data.remote.services.MatchApi
import com.eskaya.task_sanstech.domain.model.MatchMapper
import com.eskaya.task_sanstech.domain.repository.MatchRepository
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val api: MatchApi,
    private val mapper: MatchMapper
) : MatchRepository {

    override suspend fun getMatchList(): List<Match> {
        return api.getMatchList().data.map {
            mapper.fromDtoToDomain(it)
        }
    }

    /*
    override suspend fun getMovies(type: String): List<Movie> {
        return api.getMovieList(type).results.map {
            mapper.fromDtoToDomain(it)
        }
    }

     */
}