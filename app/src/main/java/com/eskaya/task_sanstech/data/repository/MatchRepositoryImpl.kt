import com.eskaya.task_sanstech.data.remote.models.response.*
import com.eskaya.task_sanstech.data.remote.services.MatchApi
import com.eskaya.task_sanstech.domain.repository.MatchRepository
import javax.inject.Inject

class MatchRepositoryImpl @Inject constructor(
    private val api: MatchApi
) : MatchRepository {

    override suspend fun getMatchList(): MatchListDto {
        return api.getMatchList()
    }
}