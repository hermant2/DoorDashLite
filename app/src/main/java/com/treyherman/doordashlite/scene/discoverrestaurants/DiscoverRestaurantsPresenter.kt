package com.treyherman.doordashlite.scene.discoverrestaurants

import android.content.res.Resources
import androidx.recyclerview.widget.DiffUtil
import com.treyherman.doordashlite.R
import com.treyherman.doordashlite.di.scope.ActivityScope
import com.treyherman.doordashlite.scene.discoverrestaurants.diff.RestaurantsDiffCallback
import com.treyherman.doordashlite.scene.discoverrestaurants.mapper.RestaurantsModelMapper
import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant
import com.treyherman.network.rest.service.restaurant.RestaurantsService
import com.treyherman.utilities.extensions.observeOnMain
import com.treyherman.utilities.extensions.subscribeOnComputation
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@ActivityScope
class DiscoverRestaurantsPresenter @Inject constructor(
    private val view: DiscoverRestaurantsMvp.View,
    private val restaurantsService: RestaurantsService,
    private val resources: Resources,
    private val restaurantsModelMapper: RestaurantsModelMapper,
    private val disposables: CompositeDisposable
) : DiscoverRestaurantsMvp.Presenter {
    companion object {
        private const val DOOR_DASH_HQ_LAT = 37.422740F
        private const val DOOR_DASH_HQ_LNG = -122.139956F
    }

    override fun onCreate() {
        view.displayLoading()
        subscribeToInitialRestaurants()
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    override fun onRefresh(currentRestaurants: List<UIRestaurant>) {
        subscribeToReloadedRestaurants(currentRestaurants)
    }

    override fun onTryAgainClicked(currentRestaurants: List<UIRestaurant>) {
        view.displayLoading()
        subscribeToReloadedRestaurants(currentRestaurants)
    }

    override fun presentRestaurantDetails(restaurantId: Long) {
        view.openRestaurantDetailScene(restaurantId)
    }

    // region private
    private fun subscribeToInitialRestaurants() {
        restaurantsSingle
            .observeOnMain()
            .subscribeWith(object : DisposableSingleObserver<List<UIRestaurant>>() {
                override fun onSuccess(restaurants: List<UIRestaurant>) {
                    view.hideLoading()
                    when (restaurants.isEmpty()) {
                        true -> view.displayEmptyContent(resources.getString(R.string.no_restaurants_found))
                        false -> view.displayRestaurants(restaurants)
                    }
                }

                override fun onError(e: Throwable) {
                    view.hideLoading()
                    view.displayEmptyContent(resources.getString(R.string.something_went_wrong))
                }
            }).addTo(disposables)
    }

    private fun subscribeToReloadedRestaurants(currentRestaurants: List<UIRestaurant>) {
        restaurantsSingle
            .flatMap { calculateRestaurantDiffOnce(currentRestaurants, it) }
            .observeOnMain()
            .subscribeWith(object :
                DisposableSingleObserver<Pair<List<UIRestaurant>, DiffUtil.DiffResult>>() {
                override fun onSuccess(restaurantDiffPair: Pair<List<UIRestaurant>, DiffUtil.DiffResult>) {
                    view.hideLoading()
                    when (restaurantDiffPair.first.isEmpty()) {
                        true -> view.displayEmptyContent(resources.getString(R.string.no_restaurants_found))
                        false -> view.updateRestaurants(restaurantDiffPair.first, restaurantDiffPair.second)
                    }
                }

                override fun onError(e: Throwable) {
                    view.hideLoading()
                    view.displayEmptyContent(resources.getString(R.string.something_went_wrong))
                }
            }).addTo(disposables)
    }

    private fun calculateRestaurantDiffOnce(
        oldRestaurants: List<UIRestaurant>,
        newRestaurants: List<UIRestaurant>
    ): Single<Pair<List<UIRestaurant>, DiffUtil.DiffResult>> {
        return Single.create<Pair<List<UIRestaurant>, DiffUtil.DiffResult>> {
            val result =
                DiffUtil.calculateDiff(RestaurantsDiffCallback(oldRestaurants, newRestaurants), true)
            it.onSuccess(Pair(newRestaurants, result))
        }.subscribeOnComputation()
    }

    private val restaurantsSingle
        get() = restaurantsService.restaurantsByLatLngOnce(DOOR_DASH_HQ_LAT, DOOR_DASH_HQ_LNG)
            .map { restaurantsModelMapper.mapRestaurants(it) }
    // endregion private
}
