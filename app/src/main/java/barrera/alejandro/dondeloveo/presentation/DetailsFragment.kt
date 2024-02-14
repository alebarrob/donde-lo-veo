package barrera.alejandro.dondeloveo.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import barrera.alejandro.dondeloveo.R
import barrera.alejandro.dondeloveo.presentation.base.BaseFragment
import barrera.alejandro.dondeloveo.databinding.FragmentDetailsBinding
import barrera.alejandro.dondeloveo.presentation.adapter.StreamingSourceAdapter
import barrera.alejandro.dondeloveo.presentation.adapter.TeamMemberAdapter
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContentDetails
import barrera.alejandro.dondeloveo.presentation.model.UiSeriesDetails
import barrera.alejandro.dondeloveo.presentation.util.showToast
import coil.load
import com.google.android.material.card.MaterialCardView
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    private val viewModel: DetailsViewModel by viewModel()
    private lateinit var imageCardView: MaterialCardView
    private lateinit var imageView: AppCompatImageView
    private lateinit var titleTextView: TextView
    private lateinit var yearTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var button: Button
    private lateinit var crewTextView: TextView
    private lateinit var crewRecyclerView: RecyclerView
    private lateinit var castTextView: TextView
    private lateinit var castRecyclerView: RecyclerView
    private lateinit var streamingSourceTextView: TextView
    private lateinit var streamingSourceRecyclerView: RecyclerView
    private lateinit var circularProgressBar: ProgressBar

    companion object {
        private const val MEDIA_CONTENT_ID = "id"
        private const val IS_FAVORITE_SCREEN = "isFavoriteScreen"
        private const val MEDIA_CONTENT_TYPE = "mediaContentType"
    }

    override fun inflate(inflater: LayoutInflater) = FragmentDetailsBinding.inflate(inflater)

    override fun setupViewBinding() {
        imageCardView = binding.imageCardView
        imageView = binding.imageView
        titleTextView = binding.titleTextView
        yearTextView = binding.yearTextView
        descriptionTextView = binding.descriptionTextView
        button = binding.button
        crewTextView = binding.crewTextView
        crewRecyclerView = binding.crewRecyclerview
        castTextView = binding.castTextView
        castRecyclerView = binding.castRecyclerview
        streamingSourceTextView = binding.streamingSourceTextView
        streamingSourceRecyclerView = binding.streamingSourceRecyclerview
        circularProgressBar = binding.circularProgressBar
    }

    override fun getBundleArguments(arguments: Bundle) {
        with(viewModel) {
            with(arguments) {
                updateMediaContentId(getInt(MEDIA_CONTENT_ID))
                getString(MEDIA_CONTENT_TYPE)?.let { mediaContentType ->
                    updateMediaContentType(mediaContentType)
                }
                updateIsFavoriteScreen(getBoolean(IS_FAVORITE_SCREEN))
            }
        }
    }

    override fun observeScreenState() {
        with(viewModel) {
            isFavoriteScreen.observe(viewLifecycleOwner) { isFavoriteScreen ->
                mediaContentId?.let {  mediaContentId ->
                    mediaContentType?.let { mediaContentType ->
                        setupButton(isFavoriteScreen)
                        if (isFavoriteScreen) {
                            loadFavoriteMediaContentDetails(mediaContentType, mediaContentId)
                        } else {
                            loadMediaContentDetails(mediaContentId)
                        }
                    }
                }
            }
            viewModel.mediaContentDetails.observe(viewLifecycleOwner) { mediaContentDetails ->
                with(mediaContentDetails) {
                    setupMediaContentImageView(imageUrl)
                    titleTextView.text = title
                    setupMediaContentYearTextView(this)
                    descriptionTextView.text = description
                    crewRecyclerView.adapter = TeamMemberAdapter(teamMemberItems = crew)
                    castRecyclerView.adapter = TeamMemberAdapter(teamMemberItems = cast)
                    streamingSourceRecyclerView.adapter = StreamingSourceAdapter(
                        streamingSourceItems = streamingSources,
                        onClickStreamingSource = { webUrl ->
                            openWebUrl(webUrl)
                        }
                    )
                }
            }
            showToastEvent.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let { text ->
                    requireContext().showToast(text)
                }
            }
            showCircularProgressBarEvent.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let { showCircularProgressBar ->
                    circularProgressBar.isVisible = showCircularProgressBar
                    imageCardView.isVisible = !showCircularProgressBar
                    button.isVisible = !showCircularProgressBar
                    crewTextView.isVisible = !showCircularProgressBar
                    castTextView.isVisible = !showCircularProgressBar
                    streamingSourceTextView.isVisible = !showCircularProgressBar
                }
            }
        }
    }

    private fun setupButton(isFavoriteScreen: Boolean) {
        with(button) {
            text = if (isFavoriteScreen) {
                getString(R.string.screen_details_button_delete_text)
            } else {
                getString(R.string.screen_details_button_favorite_text)
            }
            setOnClickListener {
                with (viewModel) {
                    if (isFavoriteScreen) onClickDelete() else onClickFavorite()
                }
            }
        }
    }

    private fun setupMediaContentImageView(url: String?) {
        imageView.load(url) {
            crossfade(true)
            placeholder(R.drawable.image_placeholder)
        }
    }

    private fun setupMediaContentYearTextView(mediaContent: UiMediaContentDetails) {
        with(mediaContent) {
            yearTextView.text = if (this is UiSeriesDetails && endYear.isNotEmpty()) {
                getString(R.string.string_hyphen_string, year, endYear)
            } else year
        }
    }

    private fun openWebUrl(webUrl: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(webUrl)))
    }
}