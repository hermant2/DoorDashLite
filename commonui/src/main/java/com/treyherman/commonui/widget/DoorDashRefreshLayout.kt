package com.treyherman.commonui.widget

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.treyherman.commonui.R
import com.treyherman.utilities.extensions.findColor

class DoorDashRefreshLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : SwipeRefreshLayout(context, attrs) {
    init {
        setColorSchemeColors(resources.findColor(R.color.color_red))
    }
}
