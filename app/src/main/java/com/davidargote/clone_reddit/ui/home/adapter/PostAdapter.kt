package com.davidargote.clone_reddit.ui.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidargote.clone_reddit.core.BaseViewHolder
import com.davidargote.clone_reddit.core.TimeUtils
import com.davidargote.clone_reddit.domain.local.PostEntity
import com.davidargote.clone_reddit.databinding.ItemPostBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class PostAdapter constructor(
    private val posts: List<PostEntity>,
    private val itemClickListener: OnPostClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnPostClickListener {
        fun onPostClick(post: PostEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = PostsViewHolder(itemBinding, parent.context)
        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { position ->
                position != DiffUtil.DiffResult.NO_POSITION
            } ?: return@setOnClickListener
            itemClickListener.onPostClick(posts[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is PostsViewHolder -> holder.bind(posts[position])
        }
    }

    override fun getItemCount(): Int = posts.size

    private inner class PostsViewHolder(val binding: ItemPostBinding, val context: Context) :
        BaseViewHolder<PostEntity>(binding.root) {

        private val player = SimpleExoPlayer.Builder(context).build()
        private val viewPlayer = binding.videoView

        @SuppressLint("SetTextI18n")
        override fun bind(item: PostEntity) {

            if (item.isVideo) {
                binding.itemImagePost.visibility = View.GONE
                val params = binding.videoView.layoutParams
                params.height = item.height
                binding.videoView.layoutParams = params
                prepareVideoPlayer(item.fallBackUrl)
            } else {
                binding.videoView.visibility = View.GONE
                Glide.with(context)
                    .load(item.url)
                    .fitCenter()
                    .dontAnimate()
                    .format(DecodeFormat.PREFER_RGB_565)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(binding.itemImagePost)
            }

            binding.itemTvRating.text = item.ups.toString()
            binding.itemTvNumComments.text = item.numComments.toString()
            binding.itemTitle.text = item.title
            binding.infoPost.text =
                "${item.subreddit} · ${TimeUtils.getTimeAgo(item.created)} · ${item.domain}"
            binding.itemUserName.text = item.subredditNamePrefixed

        }

        private fun prepareVideoPlayer(url: String) {
            viewPlayer.player = player
            val mediaItem = MediaItem.fromUri(url)
            player.setMediaItem(mediaItem)
            player.prepare()
        }

    }

}