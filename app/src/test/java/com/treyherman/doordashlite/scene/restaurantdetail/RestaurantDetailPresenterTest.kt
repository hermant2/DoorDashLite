package com.treyherman.doordashlite.scene.restaurantdetail

import android.os.Bundle
import com.nhaarman.mockito_kotlin.*
import com.treyherman.doordashlite.mocks.response.mockRestaurantResponse
import com.treyherman.doordashlite.mocks.ui.mockUIRestaurantDetail
import com.treyherman.doordashlite.rules.RxJavaTestRules
import com.treyherman.doordashlite.scene.restaurantdetail.RestaurantDetailActivity.Companion.KEY_RESTAURANT_ID
import com.treyherman.doordashlite.scene.restaurantdetail.mapper.RestaurantDetailModelMapper
import com.treyherman.network.rest.service.restaurant.RestaurantsService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(CompositeDisposable::class, Bundle::class)
class RestaurantDetailPresenterTest {

    private val view = mock<RestaurantDetailMvp.View>()
    private val restaurantsService = mock<RestaurantsService>()
    private val restaurantDetailModelMapper = mock<RestaurantDetailModelMapper>()
    private val disposables = mock<CompositeDisposable>()

    private val presenter: RestaurantDetailMvp.Presenter by lazy {
        RestaurantDetailPresenter(
            view,
            restaurantsService,
            restaurantDetailModelMapper,
            disposables
        )
    }

    private val mockExtras = mock<Bundle> {
        on { getLong(KEY_RESTAURANT_ID) }.thenReturn(1L)
    }

    private val mockRestaurantResponse by lazy { mockRestaurantResponse() }
    private val mockUIRestaurantDetail by lazy { mockUIRestaurantDetail() }

    @Rule
    val replaceSchedulers = RxJavaTestRules()

    @Test
    fun onCreate_restaurantDetailSuccess_displayRestaurantDetail() {
        whenever(restaurantsService.restaurantDetailOnce(1L))
            .thenReturn(Single.just(mockRestaurantResponse))
        whenever(restaurantDetailModelMapper.mapRestaurantDetail(mockRestaurantResponse))
            .thenReturn(mockUIRestaurantDetail)

        presenter.onCreate(mockExtras)
        verify(view).displayLoading()
        verify(restaurantsService).restaurantDetailOnce(1L)
        verify(view).hideLoading()
        verify(restaurantDetailModelMapper).mapRestaurantDetail(mockRestaurantResponse)
        verify(view).displayRestaurantDetail(mockUIRestaurantDetail)
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsService, restaurantDetailModelMapper, disposables)
    }

    @Test
    fun onCreate_restaurantDetailError_displayNonCancelableErrorDialog() {
        whenever(restaurantsService.restaurantDetailOnce(1L))
            .thenReturn(Single.error(Throwable()))

        presenter.onCreate(mockExtras)
        verify(view).displayLoading()
        verify(restaurantsService).restaurantDetailOnce(1L)
        verify(view).hideLoading()
        verify(view).displayNonCancelableErrorDialog()
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsService, restaurantDetailModelMapper, disposables)
    }

    @Test
    fun onCreate_nullExtras_displayNonCancelableErrorDialog() {
        presenter.onCreate(null)
    }

    @Test
    fun onDestroy_dispose() {
        presenter.onDestroy()
        verify(disposables).dispose()
        verifyNoMoreInteractions(disposables)
    }

    @Test
    fun onNonCancelableErrorDialogDismissed_finish() {
        presenter.onNonCancelableErrorDialogDismissed()
        verify(view).finish()
        verifyNoMoreInteractions(view)
    }

    @Test
    fun onBackNavigationClicked_finish() {
        presenter.onBackNavigationClicked()
        verify(view).finish()
        verifyNoMoreInteractions(view)
    }
}
