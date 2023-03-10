package com.prilepskiy.shopbookapp.presenter.ui.utils

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.prilepskiy.shopbookapp.presenter.ui.utils.Constants.BottomNavItems

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(
        backgroundColor = Color(0xFFF39F0D)
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()

        val currentRoute = navBackStackEntry?.destination?.route

        Constants.BottomNavItems
        BottomNavItems.forEach { navItem ->

            BottomNavigationItem(

                selected = currentRoute == navItem.route,

                onClick = {
                    navController.navigate(navItem.route)
                },

                icon = {
                    Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                },

                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }
    }
}