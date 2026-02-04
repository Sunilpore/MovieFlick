package com.movieflick.utils.preview

import androidx.compose.runtime.Composable
import com.movieflick.ui.theme.AppTheme

@Composable
fun PreviewContainer (
    content: @Composable () -> Unit
){
    AppTheme {
        content()
    }
}