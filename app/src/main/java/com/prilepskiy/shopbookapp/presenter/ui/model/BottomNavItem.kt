package com.prilepskiy.shopbookapp.presenter.ui.model

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route:String,
)