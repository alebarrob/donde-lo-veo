package barrera.alejandro.dondeloveo.core.presentation

import android.content.res.ColorStateList
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import barrera.alejandro.dondeloveo.R
import barrera.alejandro.dondeloveo.databinding.ActivityMainBinding
import barrera.alejandro.dondeloveo.explore.presentation.ExploreFragmentDirections
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarLayout: AppBarLayout
    private lateinit var materialToolbar: MaterialToolbar
    private lateinit var coordinatorLayout: CoordinatorLayout
    private lateinit var bottomAppBar: BottomAppBar
    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private var isFavoriteScreen: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setupViewBinding()
        setContentView(binding.root)

        setupNavController()
        setupBottomBar()
        onNavigationDestinationChanged()
        setupTopAppBarNavigation()
    }

    private fun setupViewBinding() {
        appBarLayout = binding.appBarLayout
        materialToolbar = binding.materialToolBar
        coordinatorLayout = binding.coordinatorLayout
        bottomAppBar = binding.bottomAppBar
        bottomNavigationView = binding.bottomNavigationView
    }

    private fun setupNavController() {
        navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setupBottomBar() {
        setupBottomAppBarAppearance()
        setupBottomAppBarNavigation()
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

    private fun setupBottomAppBarNavigation() {
        bottomNavigationView.setupWithNavController(navController)
        bottomNavigationView.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.exploreItem -> {
                    navController.navigate(
                        ExploreFragmentDirections.refreshMasterFragment(isFavoriteScreen = false)
                    )
                }

                R.id.favoriteItem -> {
                    navController.navigate(
                        ExploreFragmentDirections.refreshMasterFragment(isFavoriteScreen = true)
                    )
                }
            }
            true
        }
    }

    private fun onNavigationDestinationChanged() {
        navController.addOnDestinationChangedListener { _, destination, bundle ->
            getBundleArguments(bundle)
            when (destination.id) {
                R.id.exploreFragment -> {
                    if (isFavoriteScreen) {
                        setMenuItemChecked(R.id.favoriteItem)
                    } else {
                        setMenuItemChecked(R.id.favoriteItem)
                    }
                    appBarLayout.isVisible = false
                    coordinatorLayout.isVisible = true
                }

                R.id.detailsFragment -> {
                    appBarLayout.isVisible = true
                    coordinatorLayout.isVisible = false
                }
            }
        }
    }

    private fun getBundleArguments(bundle: Bundle?) {
        bundle?.let { arguments ->
            if (!arguments.isEmpty) {
                isFavoriteScreen = arguments.getBoolean(IS_FAVORITE_SCREEN)
            }
        }
    }

    private fun setMenuItemChecked(menuItemId: Int) {
        bottomNavigationView.menu.findItem(menuItemId)?.isChecked = true
    }

    private fun setupTopAppBarNavigation() {
        materialToolbar.setNavigationOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }

    companion object {
        private const val IS_FAVORITE_SCREEN = "isFavoriteScreen"
    }
}