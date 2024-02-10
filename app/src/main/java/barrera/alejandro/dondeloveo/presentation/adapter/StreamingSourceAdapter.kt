package barrera.alejandro.dondeloveo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import barrera.alejandro.dondeloveo.R
import barrera.alejandro.dondeloveo.databinding.StreamingSourceItemBinding
import barrera.alejandro.dondeloveo.presentation.base.BaseAdapter
import barrera.alejandro.dondeloveo.presentation.base.BaseViewHolder
import barrera.alejandro.dondeloveo.presentation.model.UiStreamingSource
import coil.load

class StreamingSourceAdapter(
    streamingSourceItems: List<UiStreamingSource>,
    private val onClickStreamingSource: (webUrl: String) -> Unit
) : BaseAdapter<UiStreamingSource, StreamingSourceItemBinding>(streamingSourceItems) {

    inner class StreamingSourceViewHolder(
        binding: StreamingSourceItemBinding
    ) : BaseViewHolder<UiStreamingSource, StreamingSourceItemBinding>(binding) {
        override fun bind(item: UiStreamingSource, position: Int) {
            with(binding) {
                with(imageView) {
                    load(item.imageUrl) {
                        crossfade(true)
                        placeholder(R.drawable.image_placeholder)
                    }
                    setOnClickListener {
                        item.webUrl?.let { onClickStreamingSource(it) }
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StreamingSourceViewHolder {
        val binding = inflateBinding(parent)
        return StreamingSourceViewHolder(binding)
    }

    override fun inflateBinding(parent: ViewGroup): StreamingSourceItemBinding {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StreamingSourceItemBinding.inflate(layoutInflater, parent, false)
    }
}