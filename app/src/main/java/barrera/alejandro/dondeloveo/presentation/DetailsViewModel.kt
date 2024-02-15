package barrera.alejandro.dondeloveo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import barrera.alejandro.dondeloveo.R
import barrera.alejandro.dondeloveo.domain.use_case.DeleteFavoriteMediaContent
import barrera.alejandro.dondeloveo.domain.use_case.GetFavoriteMediaContentDetails
import barrera.alejandro.dondeloveo.domain.use_case.GetMediaContentDetails
import barrera.alejandro.dondeloveo.domain.use_case.InsertFavoriteMediaContent
import barrera.alejandro.dondeloveo.presentation.mappers.toMediaContentDetails
import barrera.alejandro.dondeloveo.presentation.mappers.toUiMediaContentDetails
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContentDetails
import barrera.alejandro.dondeloveo.presentation.util.Event
import barrera.alejandro.dondeloveo.presentation.util.UiText
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val getMediaContentDetails: GetMediaContentDetails,
    private val insertFavoriteMediaContent: InsertFavoriteMediaContent,
    private val getFavoriteMediaContentDetails: GetFavoriteMediaContentDetails,
    private val deleteFavoriteMediaContent: DeleteFavoriteMediaContent
) : ViewModel() {
    var mediaContentId: Int? = null
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

    private val _navigateBackEvent = MutableLiveData<Event<Unit>>()
    val navigateBackEvent: LiveData<Event<Unit>>
        get() = _navigateBackEvent

    fun updateMediaContentId(mediaContentId: Int) {
        this.mediaContentId = mediaContentId
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

    fun loadFavoriteMediaContentDetails(mediaContentId: Int) {
        _showCircularProgressBarEvent.value = Event(true)
        viewModelScope.launch {
            _mediaContentDetails.value = getFavoriteMediaContentDetails.invoke(
                mediaContentId
            ).toUiMediaContentDetails()
            _showCircularProgressBarEvent.value = Event(false)
        }
    }

    fun onClickFavorite() {
        viewModelScope.launch {
            _mediaContentDetails.value?.let { mediaContentDetails ->
                insertFavoriteMediaContent(mediaContentDetails.toMediaContentDetails())
            }
            _showToastEvent.value = Event(
                UiText.StringResource(R.string.toast_added_to_favorites_text)
            )
        }
    }

    fun onClickDelete() {
        mediaContentId?.let { id ->
            viewModelScope.launch {
                deleteFavoriteMediaContent(id)
                _showToastEvent.value = Event(
                    UiText.StringResource(R.string.toast_favorite_deleted_text)
                )
                _navigateBackEvent.value = Event(Unit)
            }
        }
    }
}