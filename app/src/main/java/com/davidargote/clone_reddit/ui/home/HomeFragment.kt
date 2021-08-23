package com.davidargote.clone_reddit.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.whenCreated
import androidx.lifecycle.whenResumed
import androidx.navigation.fragment.findNavController
import com.davidargote.clone_reddit.core.Resource
import com.davidargote.clone_reddit.data.local.PostEntity
import com.davidargote.clone_reddit.data.remote.Child
import com.davidargote.clone_reddit.databinding.FragmentHomeBinding
import com.davidargote.clone_reddit.ui.home.adapter.PostAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(), PostAdapter.OnPostClickListener {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.fetchPosts()

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.whenResumed {
                viewModel.getPosts().collect { resource ->
                    when (resource) {
                        is Resource.Loading -> {
                            binding.progressBarListPosts.visibility = View.VISIBLE
                            binding.listPosts.visibility = View.GONE
                        }
                        is Resource.Success -> {
                            bindingPosts(resource.data)
                            binding.listPosts.visibility = View.VISIBLE
                            binding.progressBarListPosts.visibility = View.GONE
                        }
                        is Resource.Failure -> {
                            Log.e(TAG, resource.exception.message.toString())
                        }
                    }

                }
            }
        }
    }

    private fun bindingPosts(data: ArrayList<PostEntity>) {
        with(binding.listPosts) {
            adapter = PostAdapter(data, this@HomeFragment)
        }
    }

    override fun onPostClick(post: PostEntity) {
        val action = HomeFragmentDirections.actionHomeFragmentToPostDetailFragment(post)
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "HomeFragment"
    }

}