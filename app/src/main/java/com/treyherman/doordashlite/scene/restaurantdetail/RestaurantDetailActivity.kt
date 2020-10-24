package com.treyherman.doordashlite.scene.restaurantdetail

import com.treyherman.doordashlite.R
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity
import coil.ImageLoader
import coil.load
import com.jakewharton.rxbinding3.appcompat.navigationClicks
import com.treyherman.doordashlite.scene.restaurantdetail.model.UIRestaurantDetail

import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_restaurant_detail.*
import javax.inject.Inject

class RestaurantDetailActivity : AppCompatActivity(), RestaurantDetailMvp.View {
    companion object {
        const val KEY_RESTAURANT_ID = "restaurantId"

        fun createIntent(context: Context, restaurantId: Long): Intent =
            Intent(context, RestaurantDetailActivity::class.java)
                .putExtra(KEY_RESTAURANT_ID, restaurantId)
    }

    private var errorDialog: AlertDialog? = null

    @Inject
    lateinit var presenter: RestaurantDetailMvp.Presenter

    @Inject
    lateinit var imageLoader: ImageLoader

    private val disposables by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant_detail)
        setupView()
        presenter.onCreate(intent.extras)
    }

    override fun onDestroy() {
        errorDialog?.dismiss()
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.enter_scene_fade_in, R.anim.exit_scene_left)
    }

    override fun displayRestaurantDetail(restaurantDetail: UIRestaurantDetail) {
        ivRestaurant.load(restaurantDetail.imageUrl)
        tvRestaurantName.text = restaurantDetail.name
        tvDescription.text = restaurantDetail.description
        tvStatus.text = restaurantDetail.status
        tvDeliveryFee.text = restaurantDetail.formattedDeliveryFee
    }

    override fun displayNonCancelableErrorDialog() {
        errorDialog?.dismiss()
        errorDialog = AlertDialog.Builder(this)
            .setTitle(R.string.oops)
            .setMessage(R.string.something_went_wrong)
            .setPositiveButton(R.string.ok) { _, _ ->
                presenter.onNonCancelableErrorDialogDismissed()
            }.show()
    }

    override fun displayLoading() {
        vProgress.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        vProgress.visibility = View.GONE
    }

    // region private
    private fun setupView() {
        subscribeToNavigationClickEvents()
    }

    private fun subscribeToNavigationClickEvents() {
        tbRestaurantDetail.navigationClicks().subscribe {
            presenter.onBackNavigationClicked()
        }.addTo(disposables)
    }
    // endregion private
}
