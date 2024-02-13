package barrera.alejandro.dondeloveo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import barrera.alejandro.dondeloveo.databinding.FragmentExploreBinding
import barrera.alejandro.dondeloveo.presentation.adapter.MediaContentOverviewAdapter
import barrera.alejandro.dondeloveo.presentation.base.BaseFragment
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContent
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContentOverview
import barrera.alejandro.dondeloveo.presentation.model.UiMovieOverview
import barrera.alejandro.dondeloveo.presentation.util.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExploreFragment : BaseFragment<FragmentExploreBinding>() {
    private val viewModel: ExploreViewModel by viewModel()
    private lateinit var exploreDisplayText: TextView
    private lateinit var searchView: SearchView
    private lateinit var mediaContentRecyclerview: RecyclerView

    companion object {
        private const val IS_FAVORITE_SCREEN = "isFavoriteScreen"
        private const val MOVIE_TYPE = "Movie"
        private const val SERIES_TYPE = "Series"
    }

    override fun inflate(inflater: LayoutInflater) = FragmentExploreBinding.inflate(inflater)

    override fun setupViewBinding() {
        exploreDisplayText = binding.exploreDisplayText
        searchView = binding.searchView
        mediaContentRecyclerview = binding.mediaContentRecyclerview
    }

    override fun getBundleArguments(arguments: Bundle) {
        viewModel.updateIsFavoriteScreen(arguments.getBoolean(IS_FAVORITE_SCREEN))
    }

    override fun observeScreenState() {
        with(viewModel) {
            isFavoriteScreen.observe(viewLifecycleOwner) { isFavoriteScreen ->
                setupSearchView(isFavoriteScreen)
                setupDisplayText(isFavoriteScreen)
                if (isFavoriteScreen) loadFavoriteMediaContent() else refreshMediaContentItems()
            }
            mediaContentItems.observe(viewLifecycleOwner) { mediaContentItems ->
                setupRecyclerViewAdapter(mediaContentItems)
            }
            showToastEvent.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let { text ->
                    requireContext().showToast(text)
                }
            }
            navigateToDetailsFragmentEvent.observe(viewLifecycleOwner) { event ->
                event.getContentIfNotHandled()?.let { selectedMediaContent ->
                    navigateToDetailFragment(selectedMediaContent)
                }
            }
        }
    }

    private fun setupRecyclerViewAdapter(mediaContentItems: List<UiMediaContentOverview>) {
        val mediaContentOverviewAdapter = MediaContentOverviewAdapter(
            mediaContentItems = mediaContentItems,
            onClickMoreInfoButton = { position ->
                viewModel.onClickMoreInfoButton(position)
            }
        )

        mediaContentRecyclerview.apply {
            adapter = mediaContentOverviewAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun navigateToDetailFragment(selectedMediaContent: UiMediaContent) {
        with(viewModel) {
            with(selectedMediaContent) {
                isFavoriteScreen.value?.let { isFavoriteScreen ->
                    view?.findNavController()?.navigate(
                        ExploreFragmentDirections.navigateToDetailsFragment(
                            id = id,
                            isFavoriteScreen = isFavoriteScreen,
                            mediaContentType = if (this is UiMovieOverview) {
                                MOVIE_TYPE
                            } else {
                                SERIES_TYPE
                            }
                        )
                    )
                }
            }
        }
    }

    private fun setupSearchView(isFavoriteScreen: Boolean) {
        with(searchView) {
            visibility = if (isFavoriteScreen) View.GONE else View.VISIBLE
            findViewById<ImageView>(androidx.appcompat.R.id.search_mag_icon).setOnClickListener {
                with(query.toString()) {
                    if (isNotEmpty()) viewModel.onSearchByTitle(this)
                }
            }
            setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        query?.let { viewModel.onSearchByTitle(it) }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?) = true
                }
            )
        }
    }

    private fun setupDisplayText(isFavoriteScreen: Boolean) {
        exploreDisplayText.visibility = if (isFavoriteScreen) View.GONE else View.VISIBLE
    }
}