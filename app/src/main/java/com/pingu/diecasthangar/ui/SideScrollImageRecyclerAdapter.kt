package com.pingu.diecasthangar.ui

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.pingu.diecasthangar.R
import com.pingu.diecasthangar.data.model.Photo
import com.pingu.diecasthangar.databinding.RecyclerHorizontalImageRowLayoutBinding


class SideScrollImageRecyclerAdapter(
    private val onItemDeleted: (Photo) -> Unit,
    canDeleteItems: Boolean = false,
    val layout: String = "portrait"
): RecyclerView.Adapter<SideScrollImageRecyclerAdapter.ViewHolder>() {
    var photos = ArrayList<Photo>()
    private val canDelete = canDeleteItems

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(RecyclerHorizontalImageRowLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        @Suppress("KotlinConstantConditions")
        if (photo.localUri != null) {
            holder.photoImageView.setImageURI(photos[position].localUri)
        }
        else if (photo.localUri == null){
            val uri = Uri.parse(photo.remoteUri).toString()

            //must use placeholder so glide can get the dimensions of imageView
            Glide.with(holder.itemView.context)
                .load(uri)
                .placeholder(R.drawable.ic_airplane_black_48dp)
                .into(holder.photoImageView)
        }

        if (canDelete) {
            holder.deleteButton.visibility = View.VISIBLE
            holder.deleteButton.setOnClickListener {
                onItemDeleted(photo)
                photos.remove(photo)
                notifyItemRemoved(position)
            }
        }
        else {
            holder.deleteButton.visibility = View.GONE
        }
    }
    inner class ViewHolder(binding: RecyclerHorizontalImageRowLayoutBinding): RecyclerView.ViewHolder(binding.root),
        View.OnClickListener {

        private var view: View = binding.root

        val photoImageView: ImageView = binding.addPostRowImage
        val deleteButton: ImageButton = binding.photoDelete

        init {
            view.setOnClickListener(this)
        }
        override fun onClick(v: View?) {
        }
    }

    override fun getItemCount(): Int {
        return photos.size
    }

}