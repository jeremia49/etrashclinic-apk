package my.id.jeremia.etrash.ui.leaderboard

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import my.id.jeremia.etrash.data.model.Me
import my.id.jeremia.etrash.data.remote.apis.data.leaderboard.response.CurrentLeaderboardSuccessResponse
import my.id.jeremia.etrash.data.remote.apis.data.leaderboard.response.OldLeaderboardSuccessResponse
import my.id.jeremia.etrash.data.repository.DataRepository
import my.id.jeremia.etrash.data.repository.UserRepository
import my.id.jeremia.etrash.ui.base.BaseViewModel
import my.id.jeremia.etrash.ui.common.loader.Loader
import my.id.jeremia.etrash.ui.common.snackbar.Messenger
import my.id.jeremia.etrash.ui.homepage.HomePageViewModel
import my.id.jeremia.etrash.ui.homepage.HomePageViewModel.Companion
import my.id.jeremia.etrash.ui.navigation.Navigator
import my.id.jeremia.etrash.utils.common.CalendarUtils.getMonth
import java.time.LocalDate
import java.time.Month
import java.time.format.TextStyle
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class LeaderboardViewModel @Inject constructor(
    loader: Loader,
    val navigator: Navigator,
    val messenger: Messenger,
    @ApplicationContext val ctx: Context,
    val userRepository: UserRepository,
    val dataRepository: DataRepository,
) : BaseViewModel(loader, messenger, navigator) {

    companion object {
        const val TAG = "LeaderboardViewModel"
    }


    private val _currentLeague = MutableStateFlow("")
    private val _currentLeagueText = MutableStateFlow("")
    private val _currentLeagueList = MutableStateFlow(emptyList<CurrentLeaderboardSuccessResponse.Data>())
    private val _pastLeagueMap = MutableStateFlow(linkedMapOf<String, List<OldLeaderboardSuccessResponse.Data>>())

    val currentLeague = _currentLeague.asStateFlow()
    val currentLeagueText = _currentLeagueText.asStateFlow()
    val currentLeagueList = _currentLeagueList.asStateFlow()
    val pastLeagueMap = _pastLeagueMap.asStateFlow()

    init {
        updateMe()

        val calendar = Calendar.getInstance()
        val currentMonth = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale("id","ID"))
        val currentYear = calendar.get(Calendar.YEAR)

        viewModelScope.launch {
            _currentLeagueText.emit("${currentMonth}, ${currentYear}")
        }

        updateOngoingLeaderboard()
        updatePastLeaderboard()
    }

    fun updateMe() {
        launchNetwork {
            val meResponse = dataRepository.getMe().first().data
            _currentLeague.emit(meResponse!!.leagueBulanIni ?: "")
        }
    }

    fun updateOngoingLeaderboard(){
        launchNetwork {
            val resp = dataRepository.currentLeaderboard().first().data
            if(resp != null){
                _currentLeagueList.emit(
                    resp!!.sortedBy { it!!.rank!! } as List<CurrentLeaderboardSuccessResponse.Data>
                )
            }

        }
    }

    fun updatePastLeaderboard(){
        launchNetwork {
            val resp = dataRepository.pastLeaderboard().first().data
            if(resp != null){
                val linkedHashMap = linkedMapOf<String, List<OldLeaderboardSuccessResponse.Data>>()
                val listTahun = resp.map { it!!.tahun!! }.distinct().sortedBy { it!! }
                for(tahun in listTahun){
                    val listBulan = resp.filter { it!!.tahun == tahun }.map { it!!.bulan!! }.distinct().sortedByDescending { it!! }
                    for(bulan in listBulan){
                        val headerText = "${getMonth(bulan)}, $tahun"
                        val listRank = resp.filter { it!!.tahun == tahun && it.bulan == bulan }.sortedBy { it!!.rank!! }
                        linkedHashMap[headerText] = listRank!! as List<OldLeaderboardSuccessResponse.Data>
                    }
                }
                _pastLeagueMap.emit(linkedHashMap)
            }
        }
    }

}