package com.treyherman.doordashlite.scene.discoverrestaurants

import android.content.res.Resources
import com.nhaarman.mockito_kotlin.*
import com.treyherman.doordashlite.R
import com.treyherman.doordashlite.mocks.response.mockRestaurantResponse
import com.treyherman.doordashlite.mocks.ui.mockUIRestaurant
import com.treyherman.doordashlite.rules.RxJavaTestRules
import com.treyherman.doordashlite.scene.discoverrestaurants.mapper.RestaurantsModelMapper
import com.treyherman.network.rest.service.restaurant.RestaurantsService
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(CompositeDisposable::class)
class DiscoverRestaurantsPresenterTest {
    companion object {
        private const val EXPECTED_LAT = 37.422740F
        private const val EXPECTED_LNG = -122.139956F
        private const val SOMETHING_WENT_WRONG = "wrong"
        private const val NO_RESTAURANTS_FOUND = "no restaurants"
    }

    private val view = mock<DiscoverRestaurantsMvp.View>()
    private val restaurantsService = mock<RestaurantsService>()
    private val resources = mock<Resources> {
        on { getString(R.string.something_went_wrong) }.thenReturn(SOMETHING_WENT_WRONG)
        on { getString(R.string.no_restaurants_found) }.thenReturn(NO_RESTAURANTS_FOUND)
    }
    private val restaurantsModelMapper = mock<RestaurantsModelMapper>()
    private val disposables = mock<CompositeDisposable>()

    private val presenter: DiscoverRestaurantsMvp.Presenter by lazy {
        DiscoverRestaurantsPresenter(
            view,
            restaurantsService,
            resources,
            restaurantsModelMapper,
            disposables
        )
    }

    @Rule
    val replaceSchedulers = RxJavaTestRules()

    @Test
    fun onCreate_restaurantsByLatLngSuccessWithRestaurants_displayRestaurants() {
        val mockRestaurantResponses = listOf(mockRestaurantResponse())
        val mockUIRestaurants = listOf(mockUIRestaurant())
        whenever(restaurantsService.restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG))
            .thenReturn(Single.just(mockRestaurantResponses))
        whenever(restaurantsModelMapper.mapRestaurants(mockRestaurantResponses))
            .thenReturn(mockUIRestaurants)

        presenter.onCreate()
        verify(restaurantsService).restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG)
        verify(restaurantsModelMapper).mapRestaurants(mockRestaurantResponses)
        verify(view).displayLoading()
        verify(view).hideLoading()
        verify(view).displayRestaurants(mockUIRestaurants)
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsModelMapper, restaurantsService, disposables)
    }

    @Test
    fun onCreate_restaurantsByLatLngSuccessWithoutRestaurants_displayEmptyContent() {
        whenever(restaurantsService.restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG))
            .thenReturn(Single.just(emptyList()))
        whenever(restaurantsModelMapper.mapRestaurants(emptyList()))
            .thenReturn(emptyList())

        presenter.onCreate()
        verify(restaurantsService).restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG)
        verify(restaurantsModelMapper).mapRestaurants(emptyList())
        verify(view).displayLoading()
        verify(view).hideLoading()
        verify(view).displayEmptyContent(NO_RESTAURANTS_FOUND)
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsModelMapper, restaurantsService, disposables)
    }

    @Test
    fun onCreate_restaurantsByLatLngError_displayEmptyContent() {
        whenever(restaurantsService.restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG))
            .thenReturn(Single.error(Throwable()))

        presenter.onCreate()
        verify(restaurantsService).restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG)
        verify(view).displayLoading()
        verify(view).hideLoading()
        verify(view).displayEmptyContent(SOMETHING_WENT_WRONG)
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsModelMapper, restaurantsService, disposables)
    }

    @Test
    fun onDestroy_dispose() {
        presenter.onDestroy()
        verify(disposables).dispose()
        verifyNoMoreInteractions(disposables)
    }

    @Test
    fun onRefresh_restaurantsByLatLngSuccessWithRestaurants_updateRestaurants() {
        val mockRestaurantResponses = listOf(mockRestaurantResponse())
        val mockUIRestaurants = listOf(mockUIRestaurant())
        whenever(restaurantsService.restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG))
            .thenReturn(Single.just(mockRestaurantResponses))
        whenever(restaurantsModelMapper.mapRestaurants(mockRestaurantResponses))
            .thenReturn(mockUIRestaurants)

        presenter.onRefresh(emptyList())
        verify(restaurantsService).restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG)
        verify(restaurantsModelMapper).mapRestaurants(mockRestaurantResponses)
        verify(view).hideLoading()
        verify(view).updateRestaurants(eq(mockUIRestaurants), any())
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsModelMapper, restaurantsService, disposables)
    }

    @Test
    fun onRefresh_restaurantsByLatLngSuccessWithoutRestaurants_displayEmptyContent() {
        whenever(restaurantsService.restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG))
            .thenReturn(Single.just(emptyList()))
        whenever(restaurantsModelMapper.mapRestaurants(emptyList()))
            .thenReturn(emptyList())

        presenter.onRefresh(emptyList())
        verify(restaurantsService).restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG)
        verify(restaurantsModelMapper).mapRestaurants(emptyList())
        verify(view).hideLoading()
        verify(view).displayEmptyContent(NO_RESTAURANTS_FOUND)
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsModelMapper, restaurantsService, disposables)
    }

    @Test
    fun onRefresh_restaurantsByLatLngError_displayEmptyContent() {
        whenever(restaurantsService.restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG))
            .thenReturn(Single.error(Throwable()))

        presenter.onRefresh(emptyList())
        verify(restaurantsService).restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG)
        verify(view).hideLoading()
        verify(view).displayEmptyContent(SOMETHING_WENT_WRONG)
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsModelMapper, restaurantsService, disposables)
    }

    @Test
    fun onTryAgainClicked_restaurantsByLatLngSuccessWithRestaurants_updateRestaurants() {
        val mockRestaurantResponses = listOf(mockRestaurantResponse())
        val mockUIRestaurants = listOf(mockUIRestaurant())
        whenever(restaurantsService.restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG))
            .thenReturn(Single.just(mockRestaurantResponses))
        whenever(restaurantsModelMapper.mapRestaurants(mockRestaurantResponses))
            .thenReturn(mockUIRestaurants)

        presenter.onTryAgainClicked(emptyList())
        verify(restaurantsService).restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG)
        verify(restaurantsModelMapper).mapRestaurants(mockRestaurantResponses)
        verify(view).displayLoading()
        verify(view).hideLoading()
        verify(view).updateRestaurants(eq(mockUIRestaurants), any())
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsModelMapper, restaurantsService, disposables)
    }

    @Test
    fun onTryAgainClicked_restaurantsByLatLngSuccessWithoutRestaurants_displayEmptyContent() {
        whenever(restaurantsService.restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG))
            .thenReturn(Single.just(emptyList()))
        whenever(restaurantsModelMapper.mapRestaurants(emptyList()))
            .thenReturn(emptyList())

        presenter.onTryAgainClicked(emptyList())
        verify(restaurantsService).restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG)
        verify(restaurantsModelMapper).mapRestaurants(emptyList())
        verify(view).displayLoading()
        verify(view).hideLoading()
        verify(view).displayEmptyContent(NO_RESTAURANTS_FOUND)
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsModelMapper, restaurantsService, disposables)
    }

    @Test
    fun onTryAgainClicked_restaurantsByLatLngError_displayEmptyContent() {
        whenever(restaurantsService.restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG))
            .thenReturn(Single.error(Throwable()))

        presenter.onTryAgainClicked(emptyList())
        verify(restaurantsService).restaurantsByLatLngOnce(EXPECTED_LAT, EXPECTED_LNG)
        verify(view).displayLoading()
        verify(view).hideLoading()
        verify(view).displayEmptyContent(SOMETHING_WENT_WRONG)
        verify(disposables).add(any())
        verifyNoMoreInteractions(view, restaurantsModelMapper, restaurantsService, disposables)
    }

    @Test
    fun presentRestaurantDetails_openRestaurantDetailScene() {
        presenter.presentRestaurantDetails(1L)
        verify(view).openRestaurantDetailScene(1L)
        verifyNoMoreInteractions(view)
    }
}
