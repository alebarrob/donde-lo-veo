package barrera.alejandro.dondeloveo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.dondeloveo.domain.use_case.SaveStreamingSourceLogosUrl
import kotlinx.coroutines.launch

class MainViewModel(
    private val saveStreamingSourceLogosUrl: SaveStreamingSourceLogosUrl
) : ViewModel() {

    fun saveStreamingSourceLogosUrl() {
        viewModelScope.launch {
            saveStreamingSourceLogosUrl.invoke()
        }
    }

}