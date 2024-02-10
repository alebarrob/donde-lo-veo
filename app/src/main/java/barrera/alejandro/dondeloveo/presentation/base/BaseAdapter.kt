package barrera.alejandro.dondeloveo.presentation.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseAdapter<T, VB : ViewBinding>(
    private val items: List<T>
) : RecyclerView.Adapter<BaseViewHolder<T, VB>>() {
    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<T, VB>

    abstract fun inflateBinding(parent: ViewGroup): VB

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: BaseViewHolder<T, VB>, position: Int) {
        holder.bind(items[position], position)
    }
}