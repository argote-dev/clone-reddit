package com.davidargote.clone_reddit.ui.post_detail

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.davidargote.clone_reddit.core.TimeUtils
import com.davidargote.clone_reddit.databinding.FragmentPostDetailBinding
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView


class PostDetailFragment : Fragment() {

    private lateinit var binding: FragmentPostDetailBinding
    private val args by navArgs<PostDetailFragmentArgs>()

    private lateinit var player: SimpleExoPlayer
    private lateinit var viewPlayer: PlayerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        player = SimpleExoPlayer.Builder(view.context).build()
        viewPlayer = binding.detailVideoView

        val post = args.post

        if (post.isVideo) {
            binding.detailIvPost.visibility = View.GONE
            val params = binding.detailVideoView.layoutParams
            params.height = post.height
            binding.detailVideoView.layoutParams = params
            prepareVideoPlayer(post.fallBackUrl)
        } else {
            binding.detailVideoView.visibility = View.GONE
            Glide.with(view.context)
                .load(post.url)
                .fitCenter()
                .dontAnimate()
                .format(DecodeFormat.PREFER_RGB_565)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .into(binding.detailIvPost)
        }

        binding.detailTvTitle.text = post.title
        binding.detailTvUserName.text = post.subredditNamePrefixed
        binding.detailTvSubUser.text =
            "${post.subreddit} · ${TimeUtils.getTimeAgo(post.created)} · ${post.domain}"
        binding.detailTvNumComments.text = post.numComments.toString()
        binding.detailTvRating.text = post.ups.toString()

    }

    private fun prepareVideoPlayer(url: String) {
        viewPlayer.player = player
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
    }

    companion object {
        const val TAG = "PostDetailFragment"
    }

}