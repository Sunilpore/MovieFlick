package com.movieflick.ui.feed

import dagger.hilt.android.lifecycle.HiltViewModel
import utils.NetworkMonitor
import javax.inject.Inject


@HiltViewModel
class FeedViewModel @Inject constructor(
    val networkMonitor: NetworkMonitor
){

}