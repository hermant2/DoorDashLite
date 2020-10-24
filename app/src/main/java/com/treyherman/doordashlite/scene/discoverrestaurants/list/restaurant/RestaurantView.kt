package com.treyherman.doordashlite.scene.discoverrestaurants.list.restaurant

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import coil.ImageLoader
import coil.load
import com.jakewharton.rxbinding3.view.clicks
import com.treyherman.doordashlite.R
import com.treyherman.doordashlite.scene.discoverrestaurants.model.UIRestaurant
import dagger.android.AndroidInjector
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.item_restaurant.view.*
import javax.inject.Inject

class RestaurantView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : CardView(context, attrs, defStyle), RestaurantMvp.View {

    @Inject
    lateinit var presenter: RestaurantMvp.Presenter

    @Inject
    lateinit var imageLoader: ImageLoader

    private var clickDisposable: Disposable? = null

    fun inject(injector: AndroidInjector<RestaurantView>) {
        injector.inject(this)
    }

    fun bind(restaurant: UIRestaurant) {
        presenter.onBind(restaurant)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        subscribeToClickEvent()
    }

    override fun onDetachedFromWindow() {
        clickDisposable?.dispose()
        super.onDetachedFromWindow()
    }

    override fun displayRestaurant(restaurant: UIRestaurant) {
        ivRestaurant.load(restaurant.imageUrl, imageLoader) {
            placeholder(R.drawable.ic_food_placeholder)
        }
        tvRestaurantName.text = restaurant.name
        tvDescription.text = restaurant.foodDescription
        tvStatus.text = restaurant.status
    }

    // region private
    private fun subscribeToClickEvent() {
        clickDisposable = clicks().subscribe {
            presenter.onRestaurantClicked()
        }
    }
    // endregion private
}

