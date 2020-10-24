package com.treyherman.commonui.widget

import com.treyherman.commonui.R
import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.layout_empty_content.view.*

class EmptyContentLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : LinearLayout(context, attrs, defStyle) {
    init {
        inflate(context, R.layout.layout_empty_content, this)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.EmptyContentLayout)
        val buttonText = typedArray.getString(R.styleable.EmptyContentLayout_buttonText)
        btAction.text = buttonText
    }
}
