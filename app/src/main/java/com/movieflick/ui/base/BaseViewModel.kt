package com.movieflick.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


/**
 * Created By Sunil_P on 23/01/2026`
 */
open class BaseViewModel : ViewModel() {
    protected fun launch(block: suspend CoroutineScope.()-> Unit): Job = viewModelScope.launch (block = block)
}