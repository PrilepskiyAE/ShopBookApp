package com.prilepskiy.shopbookapp.presenter.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import com.prilepskiy.shopbookapp.presenter.ui.model.BottomNavItem


    object Constants {
        val BottomNavItems = listOf(
            BottomNavItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = "home"
            ),
            BottomNavItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = "search"
            ),
            BottomNavItem(
                label = "Favorite",
                icon = Icons.Default.Favorite,
                route = "favorite"
            )

            ,
            BottomNavItem(
                label = "Profile",
                icon = Icons.Filled.Person,
                route = "profile"
            )
        )
    }
