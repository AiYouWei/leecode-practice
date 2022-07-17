package team.opay.leecode_practice

import androidx.recyclerview.widget.RecyclerView

class SwipeCardLayoutManager : RecyclerView.LayoutManager() {
    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.WRAP_CONTENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        detachAndScrapAttachedViews(recycler)
        val bottomPosition = if (itemCount < CardConfig.MAX_SHOW_COUNT) {
            0
        } else {
            itemCount - CardConfig.MAX_SHOW_COUNT
        }
        for (i in bottomPosition..itemCount) {
            val view = recycler.getViewForPosition(i)
            addView(view)

            measureChildWithMargins(view, 0, 0)

            val widthSpace = width - getDecoratedMeasuredWidth(view)
            val heightSpace = height - getDecoratedMeasuredHeight(view)

            layoutDecoratedWithMargins(
                view,
                widthSpace / 2,
                heightSpace / 2,
                widthSpace / 2 + getDecoratedMeasuredWidth(view),
                heightSpace / 2 + getDecoratedMeasuredHeight(view)
            )
            val level = itemCount - i - 1
            if (level > 0) {
                if (level < CardConfig.MAX_SHOW_COUNT - 1) {
                    view.translationY = (CardConfig.TRANS_Y_GAP * level).toFloat()
                    view.scaleX = 1 - CardConfig.SCALE_GAP * level
                    view.scaleY = 1 - CardConfig.SCALE_GAP * level
                } else {
                    view.translationY = (CardConfig.TRANS_Y_GAP * (level - 1)).toFloat()
                    view.scaleX = 1 - CardConfig.SCALE_GAP * (level - 1)
                    view.scaleY = 1 - CardConfig.SCALE_GAP * (level - 1)
                }
            }
        }
    }
}