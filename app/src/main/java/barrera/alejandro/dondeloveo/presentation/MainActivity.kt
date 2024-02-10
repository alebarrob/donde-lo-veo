package barrera.alejandro.dondeloveo.presentation

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import barrera.alejandro.dondeloveo.R
import barrera.alejandro.dondeloveo.databinding.ActivityMainBinding
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
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.exploreFragment -> {
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

    private fun setupTopAppBarNavigation() {
        materialToolbar.setNavigationOnClickListener {
            supportFragmentManager.popBackStack()
        }
    }
}