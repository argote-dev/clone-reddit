package com.davidargote.clone_reddit.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davidargote.clone_reddit.core.Resource
import com.davidargote.clone_reddit.domain.local.PostEntity
import com.davidargote.clone_reddit.repository.post_repository.PostsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PostsRepository
) : ViewModel() {

    private val _post = MutableStateFlow<Resource<ArrayList<PostEntity>>>(Resource.Loading)

    fun fetchPosts() = viewModelScope.launch {
        kotlin.runCatching {
            repository.getPost()
        }.onSuccess { result ->
            _post.value = Resource.Success(result.first())
        }.onFailure { throwable ->
            _post.value = Resource.Failure(Exception(throwable))
        }
    }

    fun getPosts(): StateFlow<Resource<ArrayList<PostEntity>>> = _post

}