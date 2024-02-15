package barrera.alejandro.dondeloveo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import barrera.alejandro.dondeloveo.R
import barrera.alejandro.dondeloveo.presentation.base.BaseAdapter
import barrera.alejandro.dondeloveo.presentation.base.BaseViewHolder
import barrera.alejandro.dondeloveo.databinding.MediaContentItemBinding
import barrera.alejandro.dondeloveo.presentation.model.UiMediaContentOverview
import coil.load

class MediaContentOverviewAdapter(
    mediaContentItems: List<UiMediaContentOverview>,
    private val onClickMoreInfoButton: (Int) -> Unit
) : BaseAdapter<UiMediaContentOverview, MediaContentItemBinding>(mediaContentItems) {

    inner class MediaContentOverviewViewHolder(
        binding: MediaContentItemBinding
    ) : BaseViewHolder<UiMediaContentOverview, MediaContentItemBinding>(binding) {
        override fun bind(item: UiMediaContentOverview, position: Int) {
            with(binding) {
                imageView.load(item.imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                }
                titleTextView.text = item.title
                yearTextView.text = item.year

                moreInfoButton.setOnClickListener {
                    onClickMoreInfoButton(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MediaContentOverviewViewHolder {
        val binding = inflateBinding(parent)
        return MediaContentOverviewViewHolder(binding)
    }

    override fun inflateBinding(parent: ViewGroup): MediaContentItemBinding {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MediaContentItemBinding.inflate(layoutInflater, parent, false)
    }
}