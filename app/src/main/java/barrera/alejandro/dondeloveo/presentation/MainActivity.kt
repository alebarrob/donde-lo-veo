package barrera.alejandro.dondeloveo.presentation

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import barrera.alejandro.dondeloveo.R
import barrera.alejandro.dondeloveo.databinding.ActivityMainBinding
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupViewBinding()
        setContentView(binding.root)

        setupBottomBar()
    }

    private fun setupViewBinding() {
        coordinatorLayout = binding.coordinatorLayout
        bottomAppBar = binding.bottomAppBar
        bottomNavigationView = binding.bottomNavigationView
    }

    private fun setupBottomBar() {
        setupBottomAppBarAppearance()
    }

    private fun setupBottomAppBarAppearance() {
        val cornerRadius = resources.getDimension(R.dimen.medium)
        val shapeAppearanceModel = ShapeAppearanceModel.Builder()
            .setTopLeftCorner(CornerFamily.ROUNDED, cornerRadius)
            .setTopRightCorner(CornerFamily.ROUNDED, cornerRadius)
            .build()
        val shapeDrawable = MaterialShapeDrawable(shapeAppearanceModel).apply {
            strokeWidth = resources.getDimensionPixelSize(R.dimen.tiny).toFloat()
            strokeColor = ColorStateList.valueOf(applicationContext.getColor(R.color.black))
        }

        bottomAppBar.background = shapeDrawable
    }
}