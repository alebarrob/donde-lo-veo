package barrera.alejandro.dondeloveo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.dondeloveo.R
import barrera.alejandro.dondeloveo.domain.use_case.GetMediaContentDetails
import barrera.alejandro.dondeloveo.presentation.mappers.toUiMediaContentDetails
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContentDetails
import barrera.alejandro.dondeloveo.presentation.util.Event
import barrera.alejandro.dondeloveo.presentation.util.UiText
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getMediaContentDetails: GetMediaContentDetails,
) : ViewModel() {
    var mediaContentId: Int? = null
        private set

    var mediaContentType: String? = null
        private set

    private val _isFavoriteScreen = MutableLiveData(false)
    val isFavoriteScreen: LiveData<Boolean>
        get() = _isFavoriteScreen

    private val _mediaContentDetails = MutableLiveData<UiMediaContentDetails>()
    val mediaContentDetails: LiveData<UiMediaContentDetails>
        get() = _mediaContentDetails

    private val _showToastEvent = MutableLiveData<Event<UiText>>()
    val showToastEvent: LiveData<Event<UiText>>
        get() = _showToastEvent

    private val _showCircularProgressBarEvent = MutableLiveData<Event<Boolean>>()
    val showCircularProgressBarEvent: LiveData<Event<Boolean>>
        get() = _showCircularProgressBarEvent

    fun updateMediaContentId(mediaContentId: Int) {
        this.mediaContentId = mediaContentId
    }

    fun updateMediaContentType(mediaContentType: String) {
        this.mediaContentType = mediaContentType
    }

    fun updateIsFavoriteScreen(isFavoriteScreen: Boolean) {
        _isFavoriteScreen.value = isFavoriteScreen
    }

    fun loadMediaContentDetails(mediaContentId: Int) {
        _showCircularProgressBarEvent.value = Event(true)
        viewModelScope.launch {
            getMediaContentDetails.invoke(mediaContentId)
                .onSuccess { mediaContent ->
                    _mediaContentDetails.value = mediaContent.toUiMediaContentDetails()
                    _showCircularProgressBarEvent.value = Event(false)
                }
                .onFailure {
                    _showToastEvent.value = Event(UiText.StringResource(R.string.api_error))
                    _showCircularProgressBarEvent.value = Event(false)
                }
        }
    }

    // TODO("Favorites not implemented yet")
    fun loadFavoriteMediaContentDetails(mediaContentType: String, mediaContentId: Int) {

    }

    // TODO("Favorites not implemented yet")
    fun onClickFavorite() {

    }

    // TODO("Favorites not implemented yet")
    fun onClickDelete() {

    }
}