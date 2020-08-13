package com.kimi.wallet.ui.main

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Kimi.Peng on 2020/8/13.
 */
class ItemDecoration(private val dimenPixel: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(dimenPixel, dimenPixel, dimenPixel, dimenPixel)
    }

}
