package com.neugelb.themoviedb.view.components

import android.content.Context
import android.graphics.Color
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.BackgroundColorSpan
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatTextView
import com.neugelb.themoviedb.R


/**
 * Created by bishoy on 12/21/16.
 */
class HighlightTextView : AppCompatTextView {

    private val TAG = javaClass.simpleName

    private var highlight: Int? = null

    constructor(context: Context) : super(context) {
        initialize()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initializeAttributes(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, @AttrRes defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initializeAttributes(attrs)
    }

    private fun initializeAttributes(attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.HighlightTextView)
        try {
            highlight = typedArray.getColor(R.styleable.HighlightTextView_highlightColor, Color.TRANSPARENT)
        } finally {
            typedArray.recycle()
        }
        initialize()
    }

    private fun initialize() {
        setTextNew(text)
    }

    fun setTextNew(charSequence: CharSequence?) {
        if (charSequence != null) {
            if (charSequence.isNotBlank()) {
                val spannableStringBuilder = SpannableStringBuilder(charSequence)
                spannableStringBuilder.setSpan(
                    BackgroundColorSpan(highlight!!),
                    0,
                    charSequence.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                text = spannableStringBuilder
                movementMethod = LinkMovementMethod.getInstance()
            }
        }
    }

}
