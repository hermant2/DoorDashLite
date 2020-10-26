package com.treyherman.doordashlite.scene.discoverrestaurants

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DiffUtil
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import com.jakewharton.rxbinding3.view.clicks
import com.treyherman.doordashlite.R
import com.treyherman.doordashlite.scene.discoverrestaurants.list.RestaurantsAdapter
import com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant.RestaurantSubcomponent
import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant
import com.treyherman.doordashlite.scene.restaurantdetail.RestaurantDetailActivity

import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_discover_restaurants.*
import javax.inject.Inject
import javax.inject.Provider

class DiscoverRestaurantsActivity : AppCompatActivity(), DiscoverRestaurantsMvp.View {

    @Inject
    lateinit var presenter: DiscoverRestaurantsMvp.Presenter

    @Inject
    lateinit var restaurantSubcomponentFactoryProvider: Provider<RestaurantSubcomponent.Factory>

    private val adapter by lazy {
        RestaurantsAdapter(this, restaurantSubcomponentFactoryProvider.get())
    }

    private val disposables by lazy { CompositeDisposable() }

    private val tvEmptyContentMessage by lazy {
        vEmptyContent.findViewById<TextView>(R.id.tvMessage)
    }

    private val btEmptyContentAction by lazy {
        vEmptyContent.findViewById<Button>(R.id.btAction)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover_restaurants)
        setupView()
        presenter.onCreate()
    }

    override fun onDestroy() {
        disposables.dispose()
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun displayRestaurants(restaurants: List<UIRestaurant>) {
        vEmptyContent.visibility = View.GONE
        adapter.setData(restaurants)
        rvRestaurants.visibility = View.VISIBLE
    }

    override fun updateRestaurants(
        restaurants: List<UIRestaurant>,
        diffResult: DiffUtil.DiffResult
    ) {
        vEmptyContent.visibility = View.GONE
        adapter.setDataQuietly(restaurants)
        diffResult.dispatchUpdatesTo(adapter)
        rvRestaurants.visibility = View.VISIBLE
    }

    override fun displayLoading() {
        vProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        vProgress.visibility = View.GONE
        vRefresh.isRefreshing = false
    }

    override fun displayEmptyContent(message: String) {
        rvRestaurants.visibility = View.GONE
        adapter.setData(emptyList())
        tvEmptyContentMessage.text = message
        vEmptyContent.visibility = View.VISIBLE
    }

    override fun openRestaurantDetailScene(restaurantId: Long) {
        startActivity(RestaurantDetailActivity.createIntent(this, restaurantId))
        overridePendingTransition(R.anim.enter_scene_right, R.anim.scene_stand_still)
    }

    // region private
    private fun setupView() {
        rvRestaurants.adapter = adapter
        subscribeToRefreshEvent()
        subscribeToEmptyContentActionClicks()
    }

    private fun subscribeToRefreshEvent() {
        vRefresh.refreshes().subscribe {
            presenter.onRefresh(adapter.data)
        }.addTo(disposables)
    }

    private fun subscribeToEmptyContentActionClicks() {
        btEmptyContentAction.clicks().subscribe {
            presenter.onTryAgainClicked(adapter.data)
        }.addTo(disposables)
    }
    // endregion private
}
