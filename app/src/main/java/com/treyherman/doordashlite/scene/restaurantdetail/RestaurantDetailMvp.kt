package com.treyherman.doordashlite.scene.restaurantdetail

import android.os.Bundle
import com.treyherman.doordashlite.scene.restaurantdetail.model.UIRestaurantDetail


interface RestaurantDetailMvp {
    interface View {
        fun finish()

        fun displayRestaurantDetail(restaurantDetail: UIRestaurantDetail)

        fun displayNonCancelableErrorDialog()

        fun displayLoading()

        fun hideLoading()
    }

    interface Presenter {
        fun onCreate(extras: Bundle?)

        fun onDestroy()

        fun onNonCancelableErrorDialogDismissed()

        fun onBackNavigationClicked()
    }
}
