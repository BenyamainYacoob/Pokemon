package Common

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.annotation.DimenRes
import androidx.recyclerview.widget.RecyclerView

class ItemOffsetDecoration(private val itemoffset: Int) : ItemDecoration() {
    constructor(
        context: Context,
        @DimenRes dimens: Int
    ) : this(context.resources.getDimensionPixelSize(dimens)) {
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect[itemoffset, itemoffset, itemoffset] = itemoffset
    }
}