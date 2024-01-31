package barrera.alejandro.dondeloveo.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    protected val binding get() = _binding!!
    private var _binding: VB? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflate(layoutInflater)
        setupViewBinding()
        return binding.root
    }

    abstract fun inflate(inflater: LayoutInflater): VB

    abstract fun setupViewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            if (!bundle.isEmpty) getBundleArguments(bundle)
        }
        observeViewModel()
    }
    open fun getBundleArguments(arguments: Bundle) = Unit

    open fun observeViewModel() = Unit

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}