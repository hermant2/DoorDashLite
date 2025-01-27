package com.treyherman.commonui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class AbsViewHolder<T>(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(data: T)
}