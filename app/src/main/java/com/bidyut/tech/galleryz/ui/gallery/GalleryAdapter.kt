package com.bidyut.tech.galleryz.ui.gallery

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bidyut.tech.galleryz.R
import com.bidyut.tech.galleryz.model.Item
import com.squareup.picasso.Picasso

class GalleryAdapter(ctx: Context, width: Int) : RecyclerView.Adapter<ImageViewHolder>() {
    private val context: Context = ctx
    private val inflater: LayoutInflater = LayoutInflater.from(ctx)
    private val width: Int = width
    private val picasso: Picasso = Picasso.get()
    private var items: List<Item> = emptyList()

//    val spanSizeLookup: GridLayoutManager.SpanSizeLookup by lazy {
//        object : GridLayoutManager.SpanSizeLookup() {
//            override fun getSpanSize(position: Int): Int {
//                return items[position].columns
//            }
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return items[position].columns
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(inflater.inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        Log.d("---------->", "GalleryAdapter: onBindViewHolder: position = $position")
        var item1: Item? = null
        var item2: Item? = null

        if (2 * position <= items.size - 1) {
            item1 = items[2 * position]
        }
        if (2 * position < items.size - 1) {
            item2 = items[2 * position + 1]
        }

        if (item1 != null && item2 != null) {
//            holder.label1.text = item1.name
//            holder.label2.text = item2.name
            Log.d(
                "---------->",
                "GalleryAdapter: onBindViewHolder: dimension 1 = ${context.getString(
                    R.string.sss,
                    item1.width,
                    item1.height
                )}"
            )
            Log.d(
                "---------->",
                "GalleryAdapter: onBindViewHolder: dimension 2 = ${context.getString(
                    R.string.sss,
                    item2.width,
                    item2.height
                )}"
            )
            (holder.image1.layoutParams as ConstraintLayout.LayoutParams).dimensionRatio =
                context.getString(R.string.sss, item1.width, item1.height)
            (holder.image2.layoutParams as ConstraintLayout.LayoutParams).dimensionRatio =
                context.getString(R.string.sss, item2.width, item2.height)

            val h = (width / (item1.imageRatio + item2.imageRatio)).toInt()
            val firstW = (h * item1.imageRatio).toInt()
            val secondW = (h * item2.imageRatio).toInt()

            holder.image1.layoutParams.width = firstW
            holder.image1.layoutParams.height = h
            holder.image2.layoutParams.width = secondW
            holder.image2.layoutParams.height = h
            picasso.load(item1.url)
                .fit()
                .into(holder.image1)
            picasso.load(item2.url)
                .fit()
                .into(holder.image2)
            holder.image1.requestLayout()
            holder.image2.requestLayout()
            holder.image2.visibility = View.VISIBLE
        } else if (item1 != null && item2 == null) {
//            holder.label1.text = item1.name
            val firstW = width / 2
            val h = (firstW / item1.imageRatio).toInt()
            holder.image1.layoutParams.width = firstW
            holder.image1.layoutParams.height = h
            picasso.load(item1.url)
                .fit()
                .into(holder.image1)
            holder.image1.requestLayout()

            holder.image2.layoutParams.width = 0
            holder.image2.layoutParams.height = 0
            holder.image2.requestLayout()
            holder.image2.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        if (items.size % 2 == 0) {
            return items.size / 2
        } else {
            return items.size / 2 + 1
        }
    }

    fun setItems(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }
}

class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image1: ImageView = itemView.findViewById(R.id.image1)
    val image2: ImageView = itemView.findViewById(R.id.image2)
//    val label1: TextView = itemView.findViewById(R.id.label1)
//    val label2: TextView = itemView.findViewById(R.id.label2)
}
