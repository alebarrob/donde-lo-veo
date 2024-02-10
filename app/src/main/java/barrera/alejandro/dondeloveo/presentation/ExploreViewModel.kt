package barrera.alejandro.dondeloveo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContent
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContentOverview
import barrera.alejandro.dondeloveo.presentation.model.UiMovieOverview
import barrera.alejandro.dondeloveo.presentation.model.UiSeriesOverview
import barrera.alejandro.dondeloveo.presentation.util.Event
import barrera.alejandro.dondeloveo.presentation.util.UiText

class ExploreViewModel : ViewModel() {
    private val _isFavoriteScreen = MutableLiveData(false)
    val isFavoriteScreen: LiveData<Boolean>
        get() = _isFavoriteScreen

    private val _mediaContentItems = MutableLiveData<List<UiMediaContentOverview>>()
    val mediaContentItems: LiveData<List<UiMediaContentOverview>>
        get() = _mediaContentItems

    private val _showToastEvent = MutableLiveData<Event<UiText>>()
    val showToastEvent: LiveData<Event<UiText>>
        get() = _showToastEvent

    private val _navigateToDetailsFragmentEvent = MutableLiveData<Event<UiMediaContent>>()
    val navigateToDetailsFragmentEvent: LiveData<Event<UiMediaContent>>
        get() = _navigateToDetailsFragmentEvent

    fun updateIsFavoriteScreen(isFavoriteScreen: Boolean) {
        _isFavoriteScreen.value = isFavoriteScreen
    }

    // TODO("Use case and error handling not implemented yet")
    fun onSearchByTitle(query: String) {
        _mediaContentItems.value = listOf(
            UiMovieOverview(0, "Movie title", "1999", ""),
            UiSeriesOverview(0, "Series title", "1999", "")
        )
    }

    fun onClickMoreInfoButton(position: Int) {
        _mediaContentItems.value?.get(position)?.let { selectedMediaContent ->
            _navigateToDetailsFragmentEvent.value = Event(selectedMediaContent)
        }
    }

    // TODO("Use case and error handling not implemented yet")
    fun refreshMediaContentItems() {
        _mediaContentItems.value = if (_isFavoriteScreen.value == true) {
            listOf(
                UiMovieOverview(0, "Favorite movie title", "1999", ""),
                UiSeriesOverview(0, "Favorite series title", "1999", "")
            )
        } else {
            emptyList()
        }
    }
}