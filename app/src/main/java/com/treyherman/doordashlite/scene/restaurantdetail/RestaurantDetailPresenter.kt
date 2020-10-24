package com.treyherman.doordashlite.scene.restaurantdetail

import android.os.Bundle
import com.treyherman.doordashlite.di.scope.ActivityScope
import com.treyherman.doordashlite.scene.restaurantdetail.RestaurantDetailActivity.Companion.KEY_RESTAURANT_ID
import com.treyherman.doordashlite.scene.restaurantdetail.mapper.RestaurantDetailModelMapper
import com.treyherman.doordashlite.scene.restaurantdetail.model.UIRestaurantDetail
import com.treyherman.network.rest.service.restaurant.RestaurantsService
import com.treyherman.utilities.extensions.observeOnMain
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

@ActivityScope
class RestaurantDetailPresenter @Inject constructor(
    private val view: RestaurantDetailMvp.View,
    private val restaurantsService: RestaurantsService,
    private val restaurantDetailModelMapper: RestaurantDetailModelMapper,
    private val disposables: CompositeDisposable
) : RestaurantDetailMvp.Presenter {

    override fun onCreate(extras: Bundle?) {
        extras?.getLong(KEY_RESTAURANT_ID)?.let {
            view.displayLoading()
            subscribeToRestaurantDetails(it)
        } ?: view.displayNonCancelableErrorDialog()
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    override fun onNonCancelableErrorDialogDismissed() {
        view.finish()
    }

    override fun onBackNavigationClicked() {
        view.finish()
    }

    // region private
    private fun subscribeToRestaurantDetails(id: Long) {
        restaurantsService.restaurantDetailOnce(id)
            .observeOnMain()
            .map { restaurantDetailModelMapper.mapRestaurantDetail(it) }
            .subscribeWith(object : DisposableSingleObserver<UIRestaurantDetail>() {
                override fun onSuccess(detail: UIRestaurantDetail) {
                    view.hideLoading()
                    view.displayRestaurantDetail(detail)
                }

                override fun onError(e: Throwable) {
                    view.hideLoading()
                    view.displayNonCancelableErrorDialog()
                }
            }).addTo(disposables)
    }
    // endregion private
}
