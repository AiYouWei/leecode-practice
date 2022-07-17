package team.opay.leecode_practice

import android.app.Activity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import team.opay.leecode_practice.adapter.UniversalAdapter
import team.opay.leecode_practice.adapter.ViewHolder

class SwipeCardActivity : Activity() {
    private var rv: RecyclerView? = null
    private var adapter: UniversalAdapter<SwipeCardBean>? = null

    private var mDatas: List<SwipeCardBean>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe_card)

        rv = findViewById(R.id.rv)
        rv?.layoutManager = SwipeCardLayoutManager()
        mDatas = SwipeCardBean.initDatas()
        adapter = object :
            UniversalAdapter<SwipeCardBean>(this, mDatas, R.layout.item_swipe_card) {
            override fun convert(viewHolder: ViewHolder, swipeCardBean: SwipeCardBean) {

                viewHolder.setText(R.id.tvName, swipeCardBean.name)
                viewHolder.setText(
                    R.id.tvPrecent,
                    swipeCardBean.postition.toString() + "/" + mDatas.size
                )
                Glide.with(this@SwipeCardActivity)
                    .load(swipeCardBean.url)
                    .into(viewHolder.getView(R.id.iv) as ImageView)

            }
        }

        rv?.adapter = adapter

        CardConfig.initConfig(this)

        val callback = SwipeCardCallback(rv, adapter, mDatas)
        val helper = ItemTouchHelper(callback)
        helper.attachToRecyclerView(rv)
    }
}