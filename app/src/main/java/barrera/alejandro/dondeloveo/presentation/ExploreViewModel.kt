package barrera.alejandro.dondeloveo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.dondeloveo.R
import barrera.alejandro.dondeloveo.domain.use_case.GetAllFavoriteMediaContentOverview
import barrera.alejandro.dondeloveo.domain.use_case.SearchByTitle
import barrera.alejandro.dondeloveo.presentation.mappers.toUiMediaContentOverview
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContent
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContentOverview
import barrera.alejandro.dondeloveo.presentation.model.UiMovieOverview
import barrera.alejandro.dondeloveo.presentation.model.UiSeriesOverview
import barrera.alejandro.dondeloveo.presentation.util.Event
import barrera.alejandro.dondeloveo.presentation.util.UiText
import kotlinx.coroutines.launch

class ExploreViewModel(
    private val searchByTitle: SearchByTitle,
    private val getAllFavoriteMediaContentOverview: GetAllFavoriteMediaContentOverview
) : ViewModel() {
    private val _isFavoriteScreen = MutableLiveData(false)
    val isFavoriteScreen: LiveData<Boolean>
        get() = _isFavoriteScreen

    private val _mediaContentItems = MutableLiveData<List<UiMediaContentOverview>>()
    val mediaContentItems: LiveData<List<UiMediaContentOverview>>
        get() = _mediaContentItems

    private val _showToastEvent = MutableLiveData<Event<UiText>>()
    val showToastEvent: LiveData<Event<UiText>>
        get() = _showToastEvent

    private val _showCircularProgressBarEvent = MutableLiveData<Event<Boolean>>()
    val showCircularProgressBarEvent: LiveData<Event<Boolean>>
        get() = _showCircularProgressBarEvent

    private val _navigateToDetailsFragmentEvent = MutableLiveData<Event<UiMediaContent>>()
    val navigateToDetailsFragmentEvent: LiveData<Event<UiMediaContent>>
        get() = _navigateToDetailsFragmentEvent

    fun updateIsFavoriteScreen(isFavoriteScreen: Boolean) {
        _isFavoriteScreen.value = isFavoriteScreen
    }

    fun onSearchByTitle(query: String) {
        _showCircularProgressBarEvent.value = Event(true)
        viewModelScope.launch {
            searchByTitle.invoke(query)
                .onSuccess { mediaContents ->
                    _mediaContentItems.value = mediaContents.map { it.toUiMediaContentOverview() }
                    _showCircularProgressBarEvent.value = Event(false)
                }
                .onFailure {
                    _showToastEvent.value = Event(UiText.StringResource(R.string.api_error))
                    _showCircularProgressBarEvent.value = Event(false)
                }
        }
    }

    fun onClickMoreInfoButton(position: Int) {
        _mediaContentItems.value?.get(position)?.let { selectedMediaContent ->
            _navigateToDetailsFragmentEvent.value = Event(selectedMediaContent)
        }
    }

    fun loadFavoriteMediaContent() {
        viewModelScope.launch {
            _mediaContentItems.value =
                getAllFavoriteMediaContentOverview().map { it.toUiMediaContentOverview() }
        }
    }

    // TODO("Favorites not implemented yet")
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